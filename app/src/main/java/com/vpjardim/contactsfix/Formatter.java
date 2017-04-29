/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Vinícius Jardim
 * 2017/02/15
 */
public class Formatter {

    public static final String TAG = "Frmtr";

    public static class NumberParts {

        /** Error message */
        public String error;

        /** Original number unformatted */
        public String original;

        public StringBuilder cache;

        /** True if it has the add sign "+" */
        public boolean addSign;

        /** Remove all characters except + and digits */
        public String preFormatted;

        /** 0-3 digits. International direct dialing */
        public String iddCode;

        /** +(add sign) 0-3 digits  */
        public String countryCode;

        /** Country 2 letters ISO code */
        public String country;

        /** Carrier code */
        public String dddCode;

        /** 0-3 digits */
        public String areaCode;

        /** 3-4 digits */
        public String number1;

        /** 3-4 digits */
        public String number2;

        /** Final formatted number. Null if the number could not be formatted */
        public String formatted;

        public void init() {
            error = null;
            addSign = false;
            original = null;
            cache = new StringBuilder(24);
            preFormatted = null;
            iddCode = null;
            countryCode = null;
            country = null;
            dddCode = null;
            areaCode = null;
            number1 = null;
            number2 = null;
            formatted = null;
        }
    }

    public static final int MIN = 7;

    /** Matches anything except digits 0-9 */
    public static final Pattern p = Pattern.compile("[^\\d]");

    /** Matches anything except digits 0-9 and add sign "+" */
    public static final Pattern p2 = Pattern.compile("[^\\+\\d]");

    // Todo fix 2 or more add signs possible bug

    /** Default country code */
    public static String DCC = null;

    /** Default area code */
    public static String DAC = null;

    public static boolean logError(NumberParts np) {

        if(np.error != null) {
            Log.i(TAG, "Error = " + np.error);
            Log.i(TAG, "===");
            return true;
        }
        return false;
    }

    public static String format(String number, NumberParts np) {

        Log.i(TAG, "Format Call -> ");
        Log.i(TAG, "number = " + number);

        if(np == null) np = new NumberParts();

        np.init();
        np.original = number;

        preFormat(np);
        if(logError(np)) return null;
        countryCode(np);
        if(logError(np)) return null;

        if(np.countryCode == null || np.countryCode.equals("55")) {
            brazil(np);
            if(logError(np)) return null;
            assemble(np);
        }
        else if(np.countryCode.equals("1")) {
            nanp(np);
            if(logError(np)) return null;
            assemble(np);
        }

        Log.i(TAG, "===");

        return np.formatted;
    }

    public static void preFormat(NumberParts np) {

        // There is a non printable char code 8234(decimal) before the + in some contacts. Trim is
        // not removing it. Using a pattern to remove it.
        np.preFormatted = np.original.trim();
        Matcher m2 = p2.matcher(np.preFormatted);
        np.preFormatted = m2.replaceAll("");

        // Avoid empty string
        if(np.preFormatted.length() < 1) {
            np.error = "size < 1";
            return;
        }

        np.addSign = np.preFormatted.charAt(0) == '+';
        Log.i(TAG, np.preFormatted.charAt(0) + " :: " + np.addSign);

        Matcher m1 = p.matcher(np.original);

        // Replace all non digits characters with empty string
        np.preFormatted = m1.replaceAll("");
        np.cache.append(np.preFormatted);

        Log.i(TAG, "preFormatted = " + np.preFormatted);

        if(np.preFormatted.length() < MIN) np.error = "size < MIN";
    }

    public static void countryCode(NumberParts np) {

        if(!np.addSign) return;

        Tables.CRow codeRow;
        String cc;

        // Try 1 digit code
        cc = np.preFormatted.substring(0, 1);
        codeRow = Tables.countryCodes.get(cc);

        // Try 2 digits code
        if(codeRow == null) {
            cc = np.preFormatted.substring(0, 2);
            codeRow = Tables.countryCodes.get(cc);
        }

        // Try 3 digits code
        if(codeRow == null) {
            cc = np.preFormatted.substring(0, 3);
            codeRow = Tables.countryCodes.get(cc);
        }

        // If code found
        if(codeRow != null) {
            np.countryCode = codeRow.countryCode;
            np.country = codeRow.isoCode2.toLowerCase();
            np.cache.delete(0, np.countryCode.length());

            Log.i(TAG, codeRow.toString());
            Log.i(TAG, "cache = " + np.cache);
        }
    }

    public static void brazil(NumberParts np) {

        int size = np.cache.length();

        // Mobile or residential with 3 digits carrier code
        if((size == 13 || size == 14) && np.cache.charAt(0) == '0') {
            np.cache.delete(0, 3);
            size = np.cache.length();
        }

        // Mobile or residential with 2 digits area code
        if(size == 10 || size == 11) {
            np.areaCode = np.cache.substring(0, 2);
            np.cache.delete(0, 2);
            size = np.cache.length();
        }

        if(size == 9 || size == 8) {
            np.number2 = np.cache.substring(np.cache.length() -4, np.cache.length());
            np.cache.delete(np.cache.length() -4, np.cache.length());
            np.number1 = np.cache.toString();
        }
        else np.error = "Unknown format";
    }

    public static void nanp(NumberParts np) {

        int size = np.cache.length();
        np.areaCode = np.cache.substring(0, 3);
        Tables.ARow nanp = Tables.areaCodes.get(np.areaCode);

        if(size == 10 && nanp != null) {
            np.areaCode = np.cache.substring(0, 3);
            np.country = nanp.isoCode2.toLowerCase();;
            np.cache.delete(0, 3);
            np.number2 = np.cache.substring(np.cache.length() -4, np.cache.length());
            np.cache.delete(np.cache.length() -4, np.cache.length());
            np.number1 = np.cache.toString();
        }
        else {
            np.error = "Unknown format";
        }
    }

    public static void assemble(NumberParts np) {

        np.cache.delete(0, np.cache.length());

        // Todo create a method that make the validation
        if(DCC == null || DAC == null) {
            np.error = "DCC or DAC invalid";
            return;
        }

        if(np.countryCode == null) np.countryCode = DCC;
        if(np.areaCode == null) np.areaCode = DAC;

        np.cache.append("+").append(np.countryCode).append(" ").append(np.areaCode).append(" ").
                append(np.number1).append("-").append(np.number2);

        np.formatted = np.cache.toString();

        Log.i(TAG, "formatted = " + np.formatted);
    }
}
