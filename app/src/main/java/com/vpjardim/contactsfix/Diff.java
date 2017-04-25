/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import difflib.Chunk;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

/**
 * @author Vinícius Jardim
 * 2017/02/16
 */
public class Diff {

    public static final String TAG = "Diff";

    public SpannableString original;
    public SpannableString formatted;
    public int colorOriginal = Color.parseColor("#FFCDD2");
    public int colorFormatted = Color.parseColor("#C8E6C9");

    public void diffHighlight(String originalStr, String formattedStr) {

        Log.i(TAG, "diffHighlight =======>");

        original = new SpannableString(originalStr);
        formatted = new SpannableString(formattedStr);

        ArrayList<String> originalList = new ArrayList<>(Arrays.asList(originalStr.split("")));
        ArrayList<String> formattedList = new ArrayList<>(Arrays.asList(formattedStr.split("")));

        Patch<String> patch = DiffUtils.diff(originalList, formattedList);

        Log.i(TAG, "originalStr = " + originalStr);
        Log.i(TAG, "formattedStr  = " + formattedStr);
        highlight(patch);

        Log.i(TAG, "diffHighlight end ====");
    }

    private void highlight(Patch<String> patch) {

        for(Delta<String> delta : patch.getDeltas()) {

            Chunk<String> originalChunk = delta.getOriginal();
            Chunk<String> formattedChunk = delta.getRevised();

            int ini;
            int end;

            Log.i(TAG, "originalChunk = " + originalChunk);

            ini = originalChunk.getPosition() -1;
            end = ini + originalChunk.size();

            if(originalChunk.size() > 0) {
                BackgroundColorSpan span = new BackgroundColorSpan(colorOriginal);
                original.setSpan(span, ini, end, 0);
            }

            Log.i(TAG, "formattedChunk = " + formattedChunk);

            ini = formattedChunk.getPosition() -1;
            end = ini + formattedChunk.size();

            if(formattedChunk.size() > 0) {
                BackgroundColorSpan span = new BackgroundColorSpan(colorFormatted);
                formatted.setSpan(span, ini, end, 0);
            }
        }
    }
}
