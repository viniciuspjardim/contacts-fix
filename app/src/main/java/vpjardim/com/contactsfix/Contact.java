/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package vpjardim.com.contactsfix;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author Vinícius Jardim
 *         14/02/2017
 */
public class Contact implements Parcelable {

    String name;
    ArrayList<Number> numbers;

    public Contact(String name) {
        this();
        this.name = name;
    }

    public Contact() {
        numbers = new ArrayList<>();
    }

    public Number addNumber(String number, String numberFix) {
        Number n = new Number(number, numberFix);
        numbers.add(n);
        return n;
    }

    @Override
    public String toString() {
        StringBuilder strB = new StringBuilder();
        appendThis(strB);
        return new String(strB);
    }

    public void appendThis(StringBuilder strB) {
        strB.append(name).append("\n");

        for(Number number : numbers) {
            strB.append(number.number).append(" > ").append(number.numberFix);
        }
        strB.append("\n");
    }

    private Contact(Parcel in) {
        name = in.readString();
        in.readTypedList(numbers, Number.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(numbers);
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
