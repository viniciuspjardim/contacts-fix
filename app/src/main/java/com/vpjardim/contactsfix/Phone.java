/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Vinícius Jardim
 * 2017/02/17
 */
public class Phone implements Parcelable {

    private static int nextId = 1;

    public static final int NO_DIFF          = 1;
    public static final int FORMATTED        = 2;
    public static final int FORMATTED_MANUAL = 3;
    public static final int FORMAT_ERR       = 4;

    public final int id;
    public final int dataId;
    public final int rawId;
    public final String original;
    public final String formatted;
    public final String country;
    public final int status;
    public boolean toSave;

    public Phone(int dataId, int rawId, String original, String formatted, String country) {

        this.id = nextId++;
        this.dataId = dataId;
        this.rawId = rawId;
        this.original = original;
        this.formatted = formatted;
        this.country = country;

        if(formatted != null && !original.equals(formatted)) {
            status = FORMATTED;
            toSave = true;
        }
        else if(formatted == null) {
            status = FORMAT_ERR;
            toSave = false;
        }
        else {
            status = NO_DIFF;
            toSave = false;
        }
    }

    // ======== Parcelable stuff ========>

    private Phone(Parcel in) {
        id = in.readInt();
        dataId = in.readInt();
        rawId = in.readInt();
        original = in.readString();
        formatted = in.readString();
        country = in.readString();
        status = in.readInt();
        toSave = in.readInt() == 1;

        // Update the nextId in all constructors
        if(id >= nextId) nextId = id + 1;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(dataId);
        dest.writeInt(rawId);
        dest.writeString(original);
        dest.writeString(formatted);
        dest.writeString(country);
        dest.writeInt(status);
        dest.writeInt(toSave ? 1 : 0);
    }

    public static final Parcelable.Creator<Phone> CREATOR
            = new Parcelable.Creator<Phone>() {

        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };
}