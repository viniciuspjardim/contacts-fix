/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package vpjardim.com.contactsfix;

import android.text.SpannableString;

import java.util.ArrayList;

/**
 * @author Vinícius Jardim
 *         14/02/2017
 */
public class Contact {

    public static final int NUMBER_START = 0;
    public static final int NUMBER_OK = 1;
    public static final int NUMBER_FIXED = 2;
    public static final int NUMBER_ERROR = 3;

    public static class Number {
        String number;
        String numberFix;
        SpannableString numberHl;
        SpannableString numberFixHl;
        int status;

        public Number(String number) {
            this.number = number;
            this.numberFix = null;
            this.numberHl = new SpannableString(number);
            this.numberFixHl = null;
            this.status = NUMBER_START;
        }

        public Number(String number, String numberFix) {
            this.number = number;
            this.numberFix = numberFix;
            this.numberHl = new SpannableString(number);
            this.numberFixHl = new SpannableString(numberFix);

            if(numberFix == null)
                status = NUMBER_ERROR;
            else if(number.equals(numberFix))
                status = NUMBER_OK;
            else
                status = NUMBER_FIXED;
        }
    }

    String name;
    ArrayList<Number> numbers;

    public Contact(String name) {
        this();
        this.name = name;
    }

    public Contact() {
        numbers = new ArrayList<>();
    }

    public Number addNumber(String number) {
        Number n = new Number(number);
        numbers.add(n);
        return n;
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
}
