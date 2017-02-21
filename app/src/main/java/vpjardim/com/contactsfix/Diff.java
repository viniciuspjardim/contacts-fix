/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package vpjardim.com.contactsfix;

import android.graphics.Color;
import android.text.SpannableString;
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

    public SpannableString original;
    public SpannableString revised;
    public int colorOriginal = Color.parseColor("#FFCDD2");
    public int colorRevised = Color.parseColor("#C8E6C9");



    public void diffHighlight(String originalStr, String revisedStr) {

        System.out.println("diffHighlight =======>");

        original = new SpannableString(originalStr);
        revised  = new SpannableString(revisedStr);

        ArrayList<String> originalList = new ArrayList<>(Arrays.asList(originalStr.split("")));
        ArrayList<String> revisedList = new ArrayList<>(Arrays.asList(revisedStr.split("")));

        Patch<String> patch = DiffUtils.diff(originalList, revisedList);

        System.out.println("originalStr = " + originalStr);
        System.out.println("revisedStr  = " + revisedStr);
        highlight(patch);

        System.out.println("diffHighlight end ====");
    }

    private void highlight(Patch<String> patch) {

        for(Delta<String> delta : patch.getDeltas()) {

            Chunk<String> originalChunk = delta.getOriginal();
            Chunk<String> revisedChunk = delta.getRevised();

            int ini;
            int end;

            System.out.println("originalChunk = " + originalChunk);

            ini = originalChunk.getPosition() -1;
            end = ini + originalChunk.size();

            if(originalChunk.size() > 0) {
                BackgroundColorSpan span = new BackgroundColorSpan(colorOriginal);
                original.setSpan(span, ini, end, 0);
            }

            System.out.println("revisedChunk = " + revisedChunk);

            ini = revisedChunk.getPosition() -1;
            end = ini + revisedChunk.size();

            if(revisedChunk.size() > 0) {
                BackgroundColorSpan span = new BackgroundColorSpan(colorRevised);
                revised.setSpan(span, ini, end, 0);
            }
        }
    }
}
