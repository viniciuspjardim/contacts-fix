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

    // Todo use async task to load and save contacts

    public static final String TAG = "MActivity";
    public static final String CONTACTS_KEY = "CONTACTS";

    private Toolbar toolbar;
    private ViewFlipper vf;
    private FloatingActionButton startButton;
    private FloatingActionButton saveButton;
    private ListView listView;

    private Permissions permissions;
    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate(final Bundle saved) {

        super.onCreate(saved);
        Log.i(TAG, "onCreate ========>");

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        permissions = new Permissions();

        vf = (ViewFlipper) findViewById(R.id.viewFlipper);
        startButton = (FloatingActionButton) findViewById(R.id.btStart);
        saveButton = (FloatingActionButton) findViewById(R.id.btSave);

        if(saved != null)
            contacts = saved.getParcelableArrayList(CONTACTS_KEY);
        if(contacts == null)
            contacts = new ArrayList<>();

        listView = (ListView) findViewById(R.id.lvContacts);
        ContactsArrayAdapter adapter = new ContactsArrayAdapter(this, contacts);
        adapter.processSpanStrings();
        listView.setAdapter(adapter);

        // Todo fix button multi clicks problems

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                // If it already has the permission run processContacts();
                // Else, it will request the permission and wait the call back method
                if(permissions.checkPermission(
                        MainActivity.this, Permissions.READ_CONTACTS, MainActivity.this)) {
                    processContacts();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                // If it already has the permission run processContacts();
                // Else, it will request the permission and wait the call back method
                if(permissions.checkPermission(
                        MainActivity.this, Permissions.WRITE_CONTACTS, MainActivity.this)) {
                    saveContacts();
                }
            }
        });

        if(contacts.size() > 0) vf.showNext();
    }

    private void processContacts() {
        Toast.makeText(this, R.string.tt_loading, Toast.LENGTH_SHORT).show();
        ContactsLoader contactsLoader = new ContactsLoader(this, this, contacts);
        getLoaderManager().restartLoader(0, null, contactsLoader);
    }

    private void saveContacts() {
        Toast.makeText(this, R.string.tt_saving, Toast.LENGTH_SHORT).show();
        ContactsSave.save(contacts, this);
    }

    @Override
    public void onPermissionGranted(int permission) {

        if(permission == Permissions.READ_CONTACTS) {
            processContacts();
        }
        else if(permission == Permissions.WRITE_CONTACTS) {
            saveContacts();
        }
    }

    @Override
    public void onPermissionDenied(int permission) {

        if(permission == Permissions.READ_CONTACTS) {
            Toast.makeText(this, R.string.tt_err_read_contacts, Toast.LENGTH_SHORT).show();
        }
        else if(permission == Permissions.WRITE_CONTACTS) {
            Toast.makeText(this, R.string.tt_err_write_contacts, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String strPermissions[],
            int[] grantResults) {
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
