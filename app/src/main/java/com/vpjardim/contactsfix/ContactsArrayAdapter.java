/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.util.Pools;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Vinícius Jardim
 * 2017/02/14
 */
public class ContactsArrayAdapter extends ArrayAdapter<Contact> {

    public static class SpanStringHolder {

        public SpannableString original;
        public SpannableString formatted;
    }

    public static class ContactViewHolder {

        TextView contactNameTV;
        LinearLayout linearLayout;

        public ContactViewHolder(View view) {
            contactNameTV = (TextView) view.findViewById(R.id.tvContactName);
            linearLayout = (LinearLayout)view.findViewById(R.id.phoneItems);
        }
    }

    public static class PhoneViewHolder {

        TextView originalTV;
        TextView formattedTV;
        ImageView flagImg;
        CheckBox checkBox;

        public PhoneViewHolder(View view) {
            originalTV = (TextView) view.findViewById(R.id.tvOriginal);
            formattedTV = (TextView) view.findViewById(R.id.tvFormatted);
            flagImg = (ImageView) view.findViewById(R.id.flagImg);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        }
    }

    private final Pools.SynchronizedPool phoneViewPool = new Pools.SynchronizedPool(16);

    private final Context context;
    private final ArrayList<Contact> contacts;
    private final HashMap<Integer, SpanStringHolder> spanStrings;
    private final Diff diff;
    private final LayoutInflater inflater;

    public ContactsArrayAdapter(Context context, ArrayList<Contact> contacts) {

        super(context, R.layout.activity_main, contacts);

        this.context = context;
        this.contacts = contacts;

        spanStrings = new HashMap<>();
        diff = new Diff();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void processSpanStrings() {

        spanStrings.clear();

        for(Contact c : contacts) {
            for(Phone p : c.phones) {
                if(p.status != Phone.FORMATTED) continue;

                diff.diffHighlight(p.original, p.formatted);
                SpanStringHolder spans = new SpanStringHolder();
                spans.formatted = diff.formatted;
                spans.original = diff.original;
                spanStrings.put(p.id, spans);
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Performance Profiling - profile7
        // Reusing convertView, using ViewHolder to phone_item, Reusing all children of
        // linearLayout, using ViewHolder to phone_item, caching diffs

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

            PhoneViewHolder pHolder = (PhoneViewHolder)numberRow.getTag();

            numberRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "nrow", Toast.LENGTH_SHORT).show();
                }
            });

            pHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "checkBox", Toast.LENGTH_SHORT).show();
                }
            });

            pHolder.originalTV.setTextColor(Color.parseColor("#8A000000"));
            pHolder.formattedTV.setTextColor(Color.parseColor("#8A000000"));


            int id = 0;

            if(phone.country != null) {
                id = context.getResources().getIdentifier(
                        phone.country, "drawable", context.getPackageName());
            }

            if(id != 0) {
                pHolder.flagImg.setImageResource(id);
                pHolder.flagImg.setAlpha(1f);
            }
            else {
                pHolder.flagImg.setImageResource(R.drawable.flag);
                pHolder.flagImg.setAlpha(0.38f);
            }

            //if(phone.country != null) {
            //    //try {
            //        int id = context.getResources().getIdentifier(phone.country, "drawable", context.getPackageName());
            //        pHolder.flagImg.setImageResource(id);
            //    //}
            //    //catch(Exception e) {
            //    //    e.printStackTrace();
            //    //}
            //}

            if(phone.status == Phone.FORMATTED) {
                SpanStringHolder span = spanStrings.get(phone.id);

                pHolder.originalTV.setText(span.original);
                pHolder.formattedTV.setText(span.formatted);
                pHolder.checkBox.setEnabled(true);
                pHolder.checkBox.setChecked(phone.toSave);
            }
            else if(phone.status == Phone.NO_DIFF) {
                pHolder.originalTV.setText(phone.original);
                pHolder.formattedTV.setText(R.string.format_no_dif);
                pHolder.formattedTV.setTextColor(Color.parseColor("#388E3C")); // Green 700
                pHolder.checkBox.setEnabled(false);
                pHolder.checkBox.setChecked(false);
            }
            else if(phone.status == Phone.FORMAT_ERR) {
                pHolder.originalTV.setText(phone.original);
                pHolder.formattedTV.setText(R.string.format_err);
                pHolder.formattedTV.setTextColor(Color.parseColor("#D32F2F")); // Red 700
                pHolder.checkBox.setEnabled(false);
                pHolder.checkBox.setChecked(false);
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
        else {
            View view = inflater.inflate(R.layout.phone_item, parent, false);
            view.setTag(new PhoneViewHolder(view));
            return view;
        }
    }

    private void recyclePhoneView(View view) {
        phoneViewPool.release(view);
    }
}