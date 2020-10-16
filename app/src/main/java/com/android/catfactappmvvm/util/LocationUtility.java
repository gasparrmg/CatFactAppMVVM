package com.android.catfactappmvvm.util;

import android.Manifest;
import android.content.Context;
import android.os.Build;

import pub.devrel.easypermissions.EasyPermissions;

public class LocationUtility {
    public static boolean hasLocationPermissions(Context context) {
        //Check if device is working with Android Q or not
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return EasyPermissions.hasPermissions(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            );
        } else {
            return EasyPermissions.hasPermissions(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
            );
        }
    }
}
