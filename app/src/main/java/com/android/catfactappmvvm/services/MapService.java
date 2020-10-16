package com.android.catfactappmvvm.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.android.catfactappmvvm.R;
import com.android.catfactappmvvm.util.Constants;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;

import static android.app.NotificationManager.IMPORTANCE_LOW;

public class MapService extends LifecycleService {

    //Gets called whenever we send a command to our service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MapService", "Intent Action -> " + intent.getAction());
        if (intent != null) {
            switch (intent.getAction()) {
                case Constants.ACTION_START_OR_RESUME_SERVICE:
                    Log.d("MapService", "Started or resumed Service.");
                    break;
                case Constants.ACTION_PAUSE_SERVICE:
                    Log.d("MapService", "Paused Service.");
                    break;
                case Constants.ACTION_STOP_SERVICE:
                    Log.d("MapService", "Stopped Service.");
                    break;
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void startForegroundService() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, Constants.NOTIFICATION_CHANNEL_ID)
                .setAutoCancel(false)
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_baseline_map_24)
                .setContentTitle("Tracking position")
                .setContentText("00:00:00");

        //NOT FINISHED
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(NotificationManager notificationManager) {
        NotificationChannel notificationChannel = new NotificationChannel(
                Constants.NOTIFICATION_CHANNEL_ID,
                Constants.NOTIFICATION_CHANNEL_NAME,
                IMPORTANCE_LOW
        );

        notificationManager.createNotificationChannel(notificationChannel);
    }
}
