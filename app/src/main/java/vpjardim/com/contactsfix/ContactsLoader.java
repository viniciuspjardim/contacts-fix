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
 * 2017/02/14
 */
public class ContactsLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = "CLoader";

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

        String sortBy = "(UPPER(" + CommonDataKinds.Contactables.DISPLAY_NAME + ") || " +
                CommonDataKinds.Contactables.LOOKUP_KEY + ")";

        Log.i(TAG, "selection: <" + selection + ">");
        Log.i(TAG, "sortBy: <" + sortBy + ">");

        return new CursorLoader(context, ContactsContract.Data.CONTENT_URI, null, selection, null,
                sortBy);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {

        if(cursor.getCount() == 0) return;

        int phoneIndex = cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER);
        int nameIndex = cursor.getColumnIndex(CommonDataKinds.Contactables.DISPLAY_NAME);
        int lookupIndex = cursor.getColumnIndex(CommonDataKinds.Contactables.LOOKUP_KEY);
        int mimeIndex = cursor.getColumnIndex(CommonDataKinds.Contactables.MIMETYPE);

        cursor.moveToFirst();

        String prevLookupKey = "";
        Contact contact = null;
        Formatter.NumberParts np = new Formatter.NumberParts();

        do {

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

            String phone = cursor.getString(phoneIndex);
            Log.i(TAG, "phone = " + phone + " ============");

            String phoneFix = Formatter.format(phone, np);
            contact.addNumber(phone, phoneFix, np.country);

        } while(cursor.moveToNext());

        if(callback != null) callback.onLoadFinished();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {}
}
