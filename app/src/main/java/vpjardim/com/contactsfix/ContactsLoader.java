/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package vpjardim.com.contactsfix;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.util.Log;

import java.util.ArrayList;

/**
 * @author Vinícius Jardim
 * 14/02/2017
 */
public class ContactsLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = "ContactsLoader";

    public interface Callback {
        void onLoadFinished();
    }

    private Context context;
    private Callback callback;
    ArrayList<Contact> contacts;

    public ContactsLoader(Context context, Callback callback, ArrayList<Contact> contacts) {
        this.context = context;
        this.callback = callback;
        this.contacts = contacts;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderIndex, Bundle args) {

        String selection =
                "((" +
                CommonDataKinds.Contactables.DISPLAY_NAME + " NOTNULL) AND (" +
                CommonDataKinds.Contactables.DISPLAY_NAME + " != '') AND (" +
                CommonDataKinds.Contactables.HAS_PHONE_NUMBER + " = 1))";

        Log.i(TAG, selection);

        String sortBy = CommonDataKinds.Contactables.LOOKUP_KEY;

        return new CursorLoader(context, ContactsContract.Data.CONTENT_URI, null, selection, null,
                sortBy);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {

        if (cursor.getCount() == 0) return;

        int phoneIndex = cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER);
        int nameIndex = cursor.getColumnIndex(CommonDataKinds.Contactables.DISPLAY_NAME);
        int lookupIndex = cursor.getColumnIndex(CommonDataKinds.Contactables.LOOKUP_KEY);
        int typeIndex = cursor.getColumnIndex(CommonDataKinds.Contactables.MIMETYPE);

        cursor.moveToFirst();

        String lookupKey = "";
        Contact contact = null;
        Formatter.NumberParts np = new Formatter.NumberParts();

        do {
            String newLookupKey = cursor.getString(lookupIndex);

            if(!lookupKey.equals(newLookupKey)) {
                String name = cursor.getString(nameIndex);
                contact = new Contact(name);
                contacts.add(contact);
                lookupKey = newLookupKey;

                Log.i(TAG, name + "\n");
            }

            String mimeType = cursor.getString(typeIndex);

            if(mimeType.equals(CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {

                String phone = cursor.getString(phoneIndex);
                String phoneFix = Formatter.format(phone, np);

                Contact.Number n = contact.addNumber(phone, phoneFix);
                Diff.diff(n);

                Log.i(TAG, "  " + phone + "\n");
            }
        } while(cursor.moveToNext());

        if(callback != null) callback.onLoadFinished();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {}
}
