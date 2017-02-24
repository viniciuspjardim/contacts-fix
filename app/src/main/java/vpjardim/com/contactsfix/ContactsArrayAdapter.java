/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package vpjardim.com.contactsfix;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @author Vinícius Jardim
 * 14/02/2017
 */
public class ContactsArrayAdapter extends ArrayAdapter<Contact> {

    private final Context context;
    private final ArrayList<Contact> contacts;

    private Diff diff;
    LayoutInflater inflater;

    public ContactsArrayAdapter(Context context, ArrayList<Contact> contacts) {

        super(context, R.layout.activity_main, contacts);

        this.context = context;
        this.contacts = contacts;

        diff = new Diff();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView contactNameTV = (TextView) rowView.findViewById(R.id.tvContactName);
        Contact contact = contacts.get(position);

        contactNameTV.setText(contact.name);

        LinearLayout linearLayout = (LinearLayout)rowView.findViewById(R.id.phoneItems);

        for(Phone phone : contact.phones) {

            View numberRow = inflater.inflate(R.layout.phone_item, parent, false);
            TextView originalTV = (TextView) numberRow.findViewById(R.id.tvOriginal);
            TextView formattedTV = (TextView) numberRow.findViewById(R.id.tvFormatted);
            CheckBox checkBox = (CheckBox) numberRow.findViewById(R.id.checkBox);

            numberRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "nrow", Toast.LENGTH_SHORT).show();
                }
            });

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "checkBox", Toast.LENGTH_SHORT).show();
                }
            });

            if(phone.status == Phone.FORMATTED) {

                diff.diffHighlight(phone.original, phone.formatted);
                originalTV.setText(diff.original);
                formattedTV.setText(diff.formatted);
                checkBox.setChecked(phone.toSave);
            }
            else if(phone.status == Phone.NO_DIFF) {
                originalTV.setText(phone.original);
                formattedTV.setText(R.string.format_no_dif);
                formattedTV.setTextColor(Color.parseColor("#388E3C")); // Green 700
                checkBox.setEnabled(false);
                checkBox.setChecked(false);
            }
            else if(phone.status == Phone.FORMAT_ERR) {
                originalTV.setText(phone.original);
                formattedTV.setText(R.string.format_err);
                formattedTV.setTextColor(Color.parseColor("#D32F2F")); // Red 700
                checkBox.setEnabled(false);
                checkBox.setChecked(false);
            }

            linearLayout.addView(numberRow);
        }

        return rowView;
    }
}
