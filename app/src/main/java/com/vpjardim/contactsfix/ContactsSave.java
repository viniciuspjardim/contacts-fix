package com.vpjardim.contactsfix;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Contactables;
import android.util.Log;

import java.util.ArrayList;

/**
 * @author Vinícius Jardim
 * 2017/04/13
 */

public class ContactsSave {

    public static final String TAG = "CSave";

    public static void save(ArrayList<Contact> contacts, final Context context) {

        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        ContentProviderOperation.Builder op;

        for(Contact contact : contacts) {

            Log.i(TAG, "********** " + contact.name + " **********");
            Log.i(TAG, "lookupKey = " + contact.lookup);

            for(int i = 0; i < contact.phones.size(); i++) {

                Phone phone = contact.phones.get(i);
                String phoneStr = phone.formatted;
                if(phoneStr == null) phoneStr = phone.original;

                // Skip phone numbers that should not be saved
                if(phone.status != Phone.FORMATTED || !phone.toSave) continue;

                Log.i(TAG, "_ID = " + phone.dataId);
                Log.i(TAG, "RAW_CONTACT_ID = " + phone.rawId);
                Log.i(TAG, "phone.original = " + phone.original);
                Log.i(TAG, "phone.formatted = " + phone.formatted);
                Log.i(TAG, "phone.phoneStr = " + phoneStr);
                Log.i(TAG, " === ");

                op = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                        .withSelection(
                                Contactables._ID + " = ? AND " +
                                Contactables.RAW_CONTACT_ID + " = ? AND " +
                                Contactables.MIMETYPE + " = ? ",
                                new String[] {
                                        String.valueOf(phone.dataId),
                                        String.valueOf(phone.rawId),
                                        CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                                })
                        .withValue(CommonDataKinds.Phone.NUMBER, phoneStr);

                // On the last row of each contact add the Yield flag
                if(i == contact.phones.size() - 1) {
                    Log.i(TAG, "Yield... ");
                    op.withYieldAllowed(true);
                }

                ops.add(op.build());
            }
        }

        try {
            context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        }
        catch(Exception e) {
            Log.e(TAG, "Exception encountered while inserting contact: " + e);
        }
    }
}
