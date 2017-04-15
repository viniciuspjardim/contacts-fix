/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Contactables;
import android.util.Log;

import java.util.ArrayList;

/**
 * @author Vinícius Jardim
 * 2017/02/14
 */
public class ContactsLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = "CLoader";

    public interface Callback {
        void onLoadFinished();
    }

    private Context context;
    private Callback callback;
    private ArrayList<Contact> contacts;

    public ContactsLoader(Context context, Callback callback, ArrayList<Contact> contacts) {
        this.context = context;
        this.callback = callback;
        this.contacts = contacts;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderIndex, Bundle args) {

        String selection =
                "((" +
                Contactables.DISPLAY_NAME + " IS NOT NULL) AND (" +
                Contactables.DISPLAY_NAME + " != '') AND (" +
                Contactables.HAS_PHONE_NUMBER + " = 1))";

        String sortBy = "(UPPER(" + Contactables.DISPLAY_NAME + ") || " +
                Contactables.LOOKUP_KEY + ")";

        Log.i(TAG, "selection: <" + selection + ">");
        Log.i(TAG, "sortBy: <" + sortBy + ">");

        return new CursorLoader(context, ContactsContract.Data.CONTENT_URI, null, selection, null,
                sortBy);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg, final Cursor cursor) {

        if(cursor == null) {
            if(callback != null) callback.onLoadFinished();
            Log.i(TAG, "Cursor is null");
            return;
        }

        try {

            int dataIdIndex = cursor.getColumnIndex(Contactables._ID);
            int rawIdIndex = cursor.getColumnIndex(Contactables.RAW_CONTACT_ID);
            int phoneIndex = cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER);
            int nameIndex = cursor.getColumnIndex(Contactables.DISPLAY_NAME);
            int mimeIndex = cursor.getColumnIndex(Contactables.MIMETYPE);
            int lookupIndex = cursor.getColumnIndex(Contactables.LOOKUP_KEY);

            String prevLookupKey = "";
            Contact contact = null;
            Formatter.NumberParts np = new Formatter.NumberParts();

            while(cursor.moveToNext()) {

                String mimeType = cursor.getString(mimeIndex);
                if(!mimeType.equals(CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) continue;

                String lookupKey = cursor.getString(lookupIndex);

                if(!prevLookupKey.equals(lookupKey)) {

                    String name = cursor.getString(nameIndex);
                    contact = new Contact(name, lookupKey);
                    contacts.add(contact);
                    prevLookupKey = lookupKey;

                    Log.i(TAG, "********** " + name + " **********");
                    Log.i(TAG, "lookupKey = " + prevLookupKey);
                }

                int dataId = cursor.getInt(dataIdIndex);
                int rawContactId = cursor.getInt(rawIdIndex);
                String phone = cursor.getString(phoneIndex);
                Log.i(TAG, "phone = " + phone + " ============");

                String phoneFix = Formatter.format(phone, np);
                contact.addNumber(dataId, rawContactId, phone, phoneFix, np.country);
            }
        }
        finally {
            cursor.close();
        }

        if(callback != null) callback.onLoadFinished();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {}
}
