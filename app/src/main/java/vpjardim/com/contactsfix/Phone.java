/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package vpjardim.com.contactsfix;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Vinícius Jardim
 * 17/02/2017
 */
public class Phone implements Parcelable {

    public static final int NO_DIFF    = 1;
    public static final int FORMATTED  = 2;
    public static final int FORMAT_ERR = 3;

    public String original;
    public String formatted;
    public int status;
    public boolean toSave;

    public Phone(String original, String formatted) {

        this.original = original;
        this.formatted = formatted;

        if(formatted == null) {
            status = FORMAT_ERR;
            toSave = false;
        }
        else if(original.equals(formatted)) {
            status = NO_DIFF;
            toSave = false;
        }
        else {
            status = FORMATTED;
            toSave = true;
        }
    }

    // ======== Parcelable stuff ========>

    private Phone(Parcel in) {
        original = in.readString();
        formatted = in.readString();
        status = in.readInt();
        toSave = in.readInt() == 1;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(original);
        dest.writeString(formatted);
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