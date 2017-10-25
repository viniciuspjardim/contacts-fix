/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Vinícius Jardim
 * 2017/10/22
 */
public class ContactHolder extends RecyclerView.ViewHolder {

    final TextView contactNameTV;
    final LinearLayout linearLayout;

    public ContactHolder(View view) {
        super(view);
        contactNameTV = (TextView) view.findViewById(R.id.tvContactName);
        linearLayout = (LinearLayout)view.findViewById(R.id.phoneItems);
    }
}
