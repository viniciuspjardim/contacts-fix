/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author Vinícius Jardim
 * 2017/02/14
 */
public class Contact implements Parcelable {

    public final String name;
    public final String lookup;
    public final ArrayList<Phone> phones;

    public Contact(String name, String lookup) {
        this.name = name;
        this.lookup = lookup;
        this.phones = new ArrayList<>();
    }

    public void addNumber(String original, String formatted, String country) {
        phones.add(new Phone(original, formatted, country));
    }

    @Override
    public String toString() {
        StringBuilder strB = new StringBuilder();
        appendThis(strB);
        return strB.toString();
    }

    public void appendThis(StringBuilder strB) {
        strB.append(name).append("\n");

        for(Phone phone : phones) {
            strB.append(phone.original).append(" > ").append(phone.formatted);
        }
        strB.append("\n");
    }

    // ======== Parcelable stuff ========>

    private Contact(Parcel in) {
        name = in.readString();
        lookup = in.readString();
        phones = new ArrayList<>();
        in.readTypedList(phones, Phone.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lookup);
        dest.writeTypedList(phones);
    }

    public static final Parcelable.Creator<Contact> CREATOR
            = new Parcelable.Creator<Contact>() {

        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
