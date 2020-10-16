package com.android.catfactappmvvm.ui.maps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.catfactappmvvm.R;
import com.android.catfactappmvvm.services.MapService;
import com.android.catfactappmvvm.ui.home.MainActivity;
import com.android.catfactappmvvm.util.Constants;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    private Button buttonStartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        buttonStartService = findViewById(R.id.button_start_service);
        buttonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommandToService(Constants.ACTION_START_OR_RESUME_SERVICE);
            }
        });

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    private void sendCommandToService(String action) {
        Intent intent = new Intent(getBaseContext(), MapService.class);
        intent.setAction(action);
        startService(intent);
    }
}