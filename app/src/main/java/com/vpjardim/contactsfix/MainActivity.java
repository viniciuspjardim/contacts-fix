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
    // Todo after reloading all contacts should be unchecked
    // Todo landscape mode not working when rotated, text not at the center

    public static final String TAG = "MActivity";
    public static final String CONTACTS_KEY = "CONTACTS";

    private Toolbar toolbar;
    private ViewFlipper vf;
    private FloatingActionButton startButton;
    private FloatingActionButton saveButton;
    private ListView listView;

    private Permissions permissions;
    private ArrayList<Contact> contacts;
    private ContactsArrayAdapter adapter;

    @Override
    protected void onCreate(final Bundle saved) {

        super.onCreate(saved);
        Log.i(TAG, "onCreate ========>");

        setContentView(R.layout.activity_main);

        toolbar     = (Toolbar) findViewById(R.id.toolbar);
        vf          = (ViewFlipper) findViewById(R.id.viewFlipper);
        startButton = (FloatingActionButton) findViewById(R.id.btStart);
        saveButton  = (FloatingActionButton) findViewById(R.id.btSave);
        listView    = (ListView) findViewById(R.id.lvContacts);

        permissions = new Permissions();
        contacts    = new ArrayList<>();
        adapter     = new ContactsArrayAdapter(this, contacts);

        setSupportActionBar(toolbar);
        listView.setAdapter(adapter);

        // Todo fix button multi clicks problems

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                // If it already has the permission run processContacts();
                // Else, it will request the permission and wait the call back method
                if(permissions.checkPermission(
                        MainActivity.this, Permissions.READ_CONTACTS, MainActivity.this)) {
                    processContacts(true);
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
    }

    private void processContacts(boolean  showToast) {
        if(showToast)
            Toast.makeText(this, R.string.tt_loading, Toast.LENGTH_SHORT).show();

        ContactsLoader contactsLoader = new ContactsLoader(this, this, contacts);
        getLoaderManager().restartLoader(0, null, contactsLoader);
    }

    private void saveContacts() {

        Toast toast = Toast.makeText(this, R.string.tt_saving, Toast.LENGTH_SHORT);
        toast.show();

        int cont = ContactsSave.save(contacts, this);

        if(cont > 0) {

            String text = getString(R.string.tt_saved, cont);
            toast.setText(text);
            toast.show();

            contacts.clear();
            adapter.clear();
            processContacts(false);
        }
        else {
            toast.setText(R.string.tt_not_saved);
            toast.show();
        }
    }

    @Override
    public void onLoadFinished() {

        if(contacts.size() > 0) {

            ContactsArrayAdapter adapter = (ContactsArrayAdapter) listView.getAdapter();
            adapter.processSpanStrings();
            adapter.notifyDataSetChanged();
            Log.i(TAG, "Number of contacts = " + contacts.size());
            vf.setDisplayedChild(1);
        }
        else
            Toast.makeText(this, R.string.tt_no_contacts, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionGranted(int permission) {

        if(permission == Permissions.READ_CONTACTS) {
            processContacts(true);
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
    public void onSaveInstanceState(Bundle saved) {

        super.onSaveInstanceState(saved);
        Log.i(TAG, "onSaveInstanceState ========>");
        if(contacts != null && !contacts.isEmpty())
            saved.putParcelableArrayList(CONTACTS_KEY, contacts);
    }
}
