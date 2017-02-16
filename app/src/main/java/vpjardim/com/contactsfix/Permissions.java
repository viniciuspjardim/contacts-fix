/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package vpjardim.com.contactsfix;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * @author Vinícius Jardim
 *         15/02/2017
 */
public class Permissions {

    public interface Callback {
        void onPermissionGranted();
        void onPermissionDenied();
    }

    public static final int READ_CONTACTS = 1;

    public Callback callback;

    public boolean checkPermission(Activity activity, int permission, Callback callback) {

        this.callback = callback;

        if(ContextCompat.checkSelfPermission(activity, permissionCode(permission))
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACTS);
            return false;
        }
        else return true;
    }

    public String permissionCode(int localCode) {
        if(localCode == READ_CONTACTS) {
            return Manifest.permission.READ_CONTACTS;
        }
        throw new IllegalArgumentException("Invalid local permission code");
    }

    public void onPermissionsResult(int requestCode, int[] results) {
        if(
                requestCode == READ_CONTACTS &&
                results.length > 0 &&
                results[0] == PackageManager.PERMISSION_GRANTED &&
                callback != null) {
            callback.onPermissionGranted();
        }
        else {
            callback.onPermissionDenied();
        }
    }
}
