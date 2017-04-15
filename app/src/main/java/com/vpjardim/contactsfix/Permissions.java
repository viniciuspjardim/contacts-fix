/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * @author Vinícius Jardim
 * 2017/02/15
 */
public class Permissions {

    public interface Callback {
        void onPermissionGranted(int localCode);
        void onPermissionDenied(int localCode);
    }

    // Permissions local code
    public static final int READ_CONTACTS = 1;
    public static final int WRITE_CONTACTS = 2;

    public Callback callback;

    public boolean checkPermission(Activity activity, int permission, Callback callback) {

        this.callback = callback;

        if(ContextCompat.checkSelfPermission(activity, permissionCode(permission))
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, new String[] { permissionCode(permission) },
                    permission);
            return false;
        }
        else return true;
    }

    public String permissionCode(int localCode) {

        if(localCode == READ_CONTACTS) {
            return Manifest.permission.READ_CONTACTS;
        }
        else if(localCode == WRITE_CONTACTS) {
            return Manifest.permission.WRITE_CONTACTS;
        }

        throw new IllegalArgumentException("Invalid local permission code");
    }

    public void onPermissionsResult(int localCode, int[] results) {

        if(callback == null) return;
        boolean permGranted = results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED;

        if(permGranted)
            callback.onPermissionGranted(localCode);
        else
            callback.onPermissionDenied(localCode);
    }
}
