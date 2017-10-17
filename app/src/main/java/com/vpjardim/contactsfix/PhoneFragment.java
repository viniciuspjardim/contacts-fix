package com.vpjardim.contactsfix;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Vinícius Jardim
 * 2017/10/17
 */

public class PhoneFragment extends DialogFragment {

    private TextView nameTV;
    private TextView countryTV;
    private ImageView flagIV;

    private String name;
    private Phone phone;

    public void setData(String name, Phone phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_phone, null);
        builder.setView(v);

        nameTV = (TextView) v.findViewById(R.id.tvDialogContactName);
        countryTV = (TextView) v.findViewById(R.id.tvDialogCountryName);
        flagIV = (ImageView) v.findViewById(R.id.ivDialogFlag);

        nameTV.setText(name);

        if(phone.country != null)
            countryTV.setText(phone.country.toUpperCase());
        else
            countryTV.setText(R.string.tv_unknown_country);

        int id = 0;

        if(phone.country != null) {
            id = getActivity().getResources().getIdentifier(
                    "flag_" + phone.country, "drawable", getActivity().getPackageName());
        }

        if(id != 0) {
            flagIV.setImageResource(id);
            flagIV.setAlpha(1f);
        }
        else {
            flagIV.setImageResource(R.drawable.flag_00);
            flagIV.setAlpha(0.38f);
        }

        return builder.create();
    }
}
