/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package vpjardim.com.contactsfix;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.util.Pools;
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

    public static class ContactViewHolder {
        TextView contactNameTV;
        LinearLayout linearLayout;

        public ContactViewHolder(View view) {
            contactNameTV = (TextView) view.findViewById(R.id.tvContactName);
            linearLayout = (LinearLayout)view.findViewById(R.id.phoneItems);
        }
    }

    private final Pools.SynchronizedPool phoneViewPool = new Pools.SynchronizedPool(16);

    private final Context context;
    private final ArrayList<Contact> contacts;

    private Diff diff;
    private LayoutInflater inflater;

    public ContactsArrayAdapter(Context context, ArrayList<Contact> contacts) {

        super(context, R.layout.activity_main, contacts);

        this.context = context;
        this.contacts = contacts;

        diff = new Diff();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Performance Profiling - profile5
        // Reusing convertView, using ViewHolder, Reusing all children of linearLayout

        View view;
        ContactViewHolder holder;

        if(convertView == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ContactViewHolder(view);
            view.setTag(holder);
        }
        else {
            view = convertView;
            holder = (ContactViewHolder) view.getTag();
        }

        Contact contact = contacts.get(position);

        holder.contactNameTV.setText(contact.name);

        int i = 0;
        int cCount = holder.linearLayout.getChildCount();

        for(Phone phone : contact.phones) {

            View numberRow;

            if(i < cCount)
                numberRow = holder.linearLayout.getChildAt(i);
            else {
                // Getting a pooled object
                numberRow = obtainPhoneView(parent);
                holder.linearLayout.addView(numberRow);
            }

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
                // Todo set font color back to black (might be green or red)
                checkBox.setEnabled(true);
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

            i++;
        }

        // Add in the pool unused views
        for(int j = i; j < cCount; j++) {
            recyclePhoneView(holder.linearLayout.getChildAt(j));
        }

        // Removing from the LinearLayout the ones that were pooled
        if(i < cCount)
            holder.linearLayout.removeViews(i, cCount - i);

        return view;
    }

    private View obtainPhoneView(ViewGroup parent) {
        View instance = (View) phoneViewPool.acquire();

        // It will only create a new view if the pool is empty
        if(instance != null)
            // Todo should update parent?
            return instance;
        else
            return inflater.inflate(R.layout.phone_item, parent, false);
    }

    private void recyclePhoneView(View view) {
        phoneViewPool.release(view);
    }
}