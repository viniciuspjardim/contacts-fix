/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pools;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Vinícius Jardim
 * 2017/02/14
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactHolder> {

    public static final String TAG = "ContactsArrAdpt";

    private static class SpanStringHolder {
        SpannableString original;
        SpannableString formatted;
    }

    private static class PhoneViewHolder {

        private final TextView originalTV;
        private final TextView formattedTV;
        private final ImageView flagIV;
        private final CheckBox checkBox;

        public PhoneViewHolder(View view) {
            originalTV = (TextView) view.findViewById(R.id.tvOriginal);
            formattedTV = (TextView) view.findViewById(R.id.tvFormatted);
            flagIV = (ImageView) view.findViewById(R.id.ivFlag);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        }
    }

    private final Pools.SynchronizedPool phoneViewPool = new Pools.SynchronizedPool(16);

    private final Context context;
    private final ArrayList<Contact> contacts;

    private final PhoneFragment phoneFragment;
    private final HashMap<Integer, SpanStringHolder> spanStrings;
    private final Diff diff;

    public ContactsAdapter(Context context, ArrayList<Contact> contacts) {

        this.context = context;
        this.contacts = contacts;

        phoneFragment = new PhoneFragment();
        spanStrings = new HashMap<>();
        diff = new Diff();
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {

        final Contact contact = contacts.get(position);

        holder.contactNameTV.setText(contact.name);

        int i = 0;
        int cCount = holder.linearLayout.getChildCount();

        for(final Phone phone : contact.phones) {

            View numberRow;

            if(i < cCount)
                numberRow = holder.linearLayout.getChildAt(i);
            else {
                // Getting a pooled object
                numberRow = obtainPhoneView(holder.linearLayout);
                holder.linearLayout.addView(numberRow);
            }

            final PhoneViewHolder pHolder = (PhoneViewHolder)numberRow.getTag();

            numberRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "Touch phone " + phone.original + " > " + phone.formatted);
                    FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
                    phoneFragment.setData(contact.name, phone);
                    phoneFragment.show(fm, "");

                }
            });

            pHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "CheckBox " + phone.original + " > " + phone.formatted);
                    Log.i(TAG, "CheckBox checked == " + pHolder.checkBox.isChecked());
                    phone.toSave = pHolder.checkBox.isChecked();
                }
            });

            pHolder.originalTV.setTextColor(Color.parseColor("#8A000000"));
            pHolder.formattedTV.setTextColor(Color.parseColor("#8A000000"));

            int id = 0;

            if(phone.country != null) {
                id = context.getResources().getIdentifier(
                        "flag_" + phone.country, "drawable", context.getPackageName());
            }

            if(id != 0) pHolder.flagIV.setImageResource(id);
            else pHolder.flagIV.setImageResource(R.drawable.flag_00);

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

    }

    @Override
    public int getItemCount() { return contacts.size(); }

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

    private View obtainPhoneView(ViewGroup parent) {

        View instance = (View) phoneViewPool.acquire();

        // It will only create a new view if the pool is empty
        if(instance != null)
            // Todo should update parent?
            return instance;
        else {

            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.phone_item, parent, false);

            view.setTag(new PhoneViewHolder(view));
            return view;
        }
    }

    public void dialogDismiss() { phoneFragment.dismiss(); }

    private void recyclePhoneView(View view) { phoneViewPool.release(view); }

    public void clear() { spanStrings.clear(); }
}