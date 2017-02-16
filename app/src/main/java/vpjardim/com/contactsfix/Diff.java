/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package vpjardim.com.contactsfix;

import android.graphics.Color;
import android.text.style.BackgroundColorSpan;

import java.util.ArrayList;
import java.util.Arrays;

import difflib.Chunk;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

/**
 * @author Vinícius Jardim
 * 16/02/2017
 */
public class Diff {

    public static final String ORIGINAL_START = "<span style=\"background-color:#880000\">";
    public static final String ORIGINAL_END = "</span>";
    public static final String REVISED_START = "<span style=\"background-color:#008800\">";
    public static final String REVISED_END = "</span>";

    Object greenSpan = new BackgroundColorSpan(Color.GREEN);
    Object redSpan = new BackgroundColorSpan(Color.RED);

    // public static final String ORIGINAL_START = "[";
    // public static final String ORIGINAL_END = "]";
    // public static final String REVISED_START = "[";
    // public static final String REVISED_END = "]";

    public static void main(String[] args) {

        //                 01234567890123456
        String original = "+1 314 488 6534";
        String revised  = "+55 13 14488-6534";

        // //                 01234567890123456
        // String original = "14 (63) 984568863";
        // String revised  = "+55 63 98456-8863";

        ArrayList<String> originalList = new ArrayList<>(Arrays.asList(original.split("")));
        ArrayList<String> revisedList = new ArrayList<>(Arrays.asList(revised.split("")));

        Patch<String> patch = DiffUtils.diff(originalList, revisedList);

        System.out.println(highlight(original, patch, true));
        System.out.println(highlight(revised, patch, false));
    }

    public static void diff(Contact.Number number) {

        if(number.status != Contact.NUMBER_FIXED) return;

        String original = number.number;
        String revised  = number.numberFix;

        ArrayList<String> originalList = new ArrayList<>(Arrays.asList(original.split("")));
        ArrayList<String> revisedList = new ArrayList<>(Arrays.asList(revised.split("")));

        Patch<String> patch = DiffUtils.diff(originalList, revisedList);

        number.numberHl = highlight(original, patch, true);
        number.numberFixHl = highlight(revised, patch, false);
    }

    private static String highlight(String srt, Patch<String> patch, boolean isOriginal) {

        StringBuilder result = new StringBuilder();
        int prevEnd = 0;

        String startCode;
        String endCode;

        if(isOriginal) {
            startCode = ORIGINAL_START;
            endCode = ORIGINAL_END;
        }
        else {
            startCode = REVISED_START;
            endCode = REVISED_END;
        }

        for(Delta<String> delta : patch.getDeltas()) {

            Chunk<String> chunk;
            if(isOriginal) chunk = delta.getOriginal();
            else chunk = delta.getRevised();

            int ini = chunk.getPosition() -1;
            int end = ini + chunk.size() -1;

            if(chunk.size() > 0) {
                // What is left behind
                result.append(srt.substring(prevEnd, ini));
                // Changed start delimiter
                result.append(startCode);
                // Content changed
                //try {
                    result.append(srt.substring(ini, end));
                //}
                //catch(Exception e) { }
                // Change end delimiter
                result.append(endCode);

                prevEnd = end;
            }
        }

        result.append(srt.substring(prevEnd, srt.length()));

        return result.toString();
    }
}
