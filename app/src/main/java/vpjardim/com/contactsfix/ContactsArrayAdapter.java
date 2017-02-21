/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package vpjardim.com.contactsfix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Vinícius Jardim
 *         14/02/2017
 */
public class ContactsArrayAdapter extends ArrayAdapter<Contact> {

    private final Context context;
    private final ArrayList<Contact> contacts;

    public ContactsArrayAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, R.layout.activity_main, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Diff diff = new Diff();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView nameTextView = (TextView) rowView.findViewById(R.id.name);

        Contact contact = contacts.get(position);

        nameTextView.setText(contact.name);

        for(Number number : contact.numbers) {

            View numberRow = inflater.inflate(R.layout.phone_item, parent, false);
            TextView phoneTextView = (TextView) numberRow.findViewById(R.id.phone);
            TextView phoneFixTextView = (TextView) numberRow.findViewById(R.id.phoneFix);

            if(number.status == Number.FORMATTED) {

                diff.diffHighlight(number.number, number.numberFix);

                phoneTextView.setText(diff.original);
                phoneFixTextView.setText(diff.revised);
            }
            else if(number.status == Number.NO_DIFF) {
                phoneTextView.setText(number.number);
                phoneFixTextView.setText(R.string.format_no_dif);
            }
            else if(number.status == Number.FORMAT_ERR) {
                phoneTextView.setText(number.number);
                phoneFixTextView.setText(R.string.format_err);
            }

            LinearLayout linearLayout = (LinearLayout)rowView.findViewById(R.id.listPhoneItems);
            linearLayout.addView(numberRow);
        }

        return rowView;
    }
}
