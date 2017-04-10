/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

/**
 * @author Vinícius Jardim
 * 2017/02/14
 */
public class MainActivity extends AppCompatActivity implements Permissions.Callback,
        ContactsLoader.Callback {

    public static final String TAG = "MActivity";
    public static final String CONTACTS_KEY = "CONTACTS";

    Toolbar toolbar;
    ViewFlipper vf;
    FloatingActionButton startButton;
    ListView listView;

    Permissions permissions;
    ArrayList<Contact> contacts;


    @Override
    protected void onCreate(Bundle saved) {

        super.onCreate(saved);
        Log.i(TAG, "onCreate ========>");

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        permissions = new Permissions();

        vf = (ViewFlipper) findViewById(R.id.viewFlipper);
        startButton = (FloatingActionButton) findViewById(R.id.btStart);

        if(saved != null)
            contacts = saved.getParcelableArrayList(CONTACTS_KEY);
        if(contacts == null)
            contacts = new ArrayList<>();

        listView = (ListView) findViewById(R.id.lvContacts);
        ContactsArrayAdapter adapter = new ContactsArrayAdapter(this, contacts);
        adapter.processSpanStrings();
        listView.setAdapter(adapter);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startButton.setActivated(false);

                if(permissions.checkPermission(
                        MainActivity.this, Permissions.READ_CONTACTS, MainActivity.this)) {
                    processContacts();
                }
                startButton.setActivated(true);
            }
        });

        if(contacts.size() > 0) vf.showNext();
    }

    public void processContacts() {
        Toast.makeText(this, R.string.tt_loading, Toast.LENGTH_SHORT).show();
        ContactsLoader contactsLoader = new ContactsLoader(this, this, contacts);
        getLoaderManager().restartLoader(0, null, contactsLoader);
    }

    @Override
    public void onPermissionGranted() { processContacts(); }

    @Override
    public void onPermissionDenied() {
        Toast.makeText(this, R.string.tt_no_permission, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String strPermissions[], int[] grantResults) {
        permissions.onPermissionsResult(requestCode, grantResults);
    }

    @Override
    public void onLoadFinished() {

        if(contacts.size() > 0) {

            ContactsArrayAdapter adapter = (ContactsArrayAdapter) listView.getAdapter();
            adapter.processSpanStrings();
            adapter.notifyDataSetChanged();
            Log.i(TAG, "Number of contacts = " + contacts.size());
            vf.showNext();
        }
        else
            Toast.makeText(this, R.string.tt_no_contacts, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle saved) {

        super.onSaveInstanceState(saved);
        Log.i(TAG, "onSaveInstanceState ========>");
        if(contacts != null && !contacts.isEmpty())
            saved.putParcelableArrayList(CONTACTS_KEY, contacts);
    }
}
