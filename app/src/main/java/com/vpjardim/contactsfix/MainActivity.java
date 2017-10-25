/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @author Vinícius Jardim
 * 2017/02/14
 */
public class MainActivity extends AppCompatActivity implements Permissions.Callback,
        ContactsLoader.Callback {

    // Todo use async task to load and save contacts
    // Todo after reloading all phone numbers should be unchecked
    // Todo default country code and area code chooser dialog
    // Todo flags shadows
    // Todo duplicated phone numbers in some contacts like in 'Alessandra'. Maybe it's WhatsApp
    // Todo implement a custom scrollbar
    // Todo fix dialog close on orientation change
    // Todo fix bug that permission dialog is shown again on orientation change
    // Todo improve empty state screen design

    public static final String TAG = "MActivity";
    public static final String CONTACTS_KEY = "CONTACTS";

    private Toolbar toolbar;
    private FloatingActionButton saveButton;
    private RecyclerView recyclerView;
    private TextView errorTV;

    private ArrayList<Contact> contacts;
    private Permissions permissions;
    private ContactsAdapter adapter;

    @Override
    protected void onCreate(final Bundle saved) {

        super.onCreate(saved);
        Log.i(TAG, "onCreate ========>");

        setContentView(R.layout.activity_main);

        toolbar      = (Toolbar) findViewById(R.id.toolbar);
        saveButton   = (FloatingActionButton) findViewById(R.id.btSave);
        recyclerView = (RecyclerView) findViewById(R.id.rvContacts);
        errorTV      = (TextView) findViewById(R.id.tvError);

        setSupportActionBar(toolbar);

        // There are things saved when the screen is rotated for example. Then put the stuff in
        // contacts array to not lose state
        if(saved != null)
            contacts = saved.getParcelableArrayList(CONTACTS_KEY);
        if(contacts == null)
            contacts = new ArrayList<>();

        permissions = new Permissions();
        adapter     = new ContactsAdapter(this, contacts);

        // If the contacts are loaded, process and show the contacts display view
        if(contacts.size() > 0) {
            adapter.processSpanStrings();
            recyclerView.setVisibility(View.VISIBLE);
        }
        // Otherwise show error message
        else errorTV.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);

        // Todo fix button multi clicks problems

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

        Intent intent = getIntent();
        Formatter.DCC = intent.getStringExtra("DCC");
        Formatter.DAC = intent.getStringExtra("DAC");

        // If it already has the permission run processContacts();
        // Else, it will request the permission and wait the call back method
        if(contacts.size() == 0 && permissions.checkPermission(
                MainActivity.this, Permissions.READ_CONTACTS, MainActivity.this)) {
            adapter.processSpanStrings();
            processContacts();
        }
    }

    private void processContacts() {

        // Checking if default country code is ok
        Tables.CRow cRow = Tables.countryCodes.get(Formatter.DCC);
        if(cRow == null) {
            Toast.makeText(this, R.string.tt_err_country_code, Toast.LENGTH_SHORT).show();
            return;
        }

        // Checking if default area code is ok
        Tables.ARow aRow = Tables.areaCodes.get(Formatter.DCC + "/" + Formatter.DAC);
        if(aRow == null) {
            Toast.makeText(this, R.string.tt_err_area_code, Toast.LENGTH_SHORT).show();
            return;
        }

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
            processContacts();
        }
        else {
            toast.setText(R.string.tt_not_saved);
            toast.show();
        }
    }

    @Override
    public void onLoadFinished() {

        if(contacts.size() > 0) {

            ContactsAdapter adapter = (ContactsAdapter) recyclerView.getAdapter();
            adapter.processSpanStrings();
            adapter.notifyDataSetChanged();

            recyclerView.setVisibility(View.VISIBLE);
            errorTV.setVisibility(View.GONE);

            Log.i(TAG, "Number of contacts = " + contacts.size());
        }
        else {
            recyclerView.setVisibility(View.GONE);
            errorTV.setText(getResources().getString(R.string.tt_no_contacts));
            errorTV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String strPermissions[],
            int[] grantResults) {
        permissions.onPermissionsResult(requestCode, grantResults);
    }

    @Override
    public void onPause() {
        super.onPause();
        adapter.dialogDismiss();
    }

    @Override
    public void onSaveInstanceState(Bundle saved) {

        super.onSaveInstanceState(saved);
        Log.i(TAG, "onSaveInstanceState ========>");
        if(contacts != null && !contacts.isEmpty())
            saved.putParcelableArrayList(CONTACTS_KEY, contacts);
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
            recyclerView.setVisibility(View.GONE);
            errorTV.setText(getResources().getString(R.string.tt_err_read_contacts));
            errorTV.setVisibility(View.VISIBLE);
        }
        else if(permission == Permissions.WRITE_CONTACTS) {
            recyclerView.setVisibility(View.GONE);
            errorTV.setText(getResources().getString(R.string.tt_err_write_contacts));
            errorTV.setVisibility(View.VISIBLE);
        }
    }
}
