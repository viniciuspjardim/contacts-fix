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
public class Number implements Parcelable {

    public static final int START      = 0;
    public static final int NO_DIFF    = 1;
    public static final int FORMATTED  = 2;
    public static final int FORMAT_ERR = 3;

    String number;
    String numberFix;
    int status;

    public Number(String number) {
        this.number = number;
        this.numberFix = null;
        this.status = START;
    }

    public Number(String number, String numberFix) {
        this.number = number;
        this.numberFix = numberFix;

        if(numberFix == null)
            status = FORMAT_ERR;
        else if(number.equals(numberFix))
            status = NO_DIFF;
        else
            status = FORMATTED;
    }

    private Number(Parcel in) {
        number = in.readString();
        numberFix = in.readString();
        status = in.readInt();
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeString(numberFix);
        dest.writeInt(status);
    }

    public static final Parcelable.Creator<Number> CREATOR
            = new Parcelable.Creator<Number>() {

        @Override
        public Number createFromParcel(Parcel in) {
            return new Number(in);
        }

        @Override
        public Number[] newArray(int size) {
            return new Number[size];
        }
    };
}
