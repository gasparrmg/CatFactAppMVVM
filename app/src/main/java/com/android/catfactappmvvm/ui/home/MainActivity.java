package com.android.catfactappmvvm.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.catfactappmvvm.R;
import com.android.catfactappmvvm.data.remote.models.Fact;
import com.android.catfactappmvvm.ui.home.adapters.FactAdapter;
import com.android.catfactappmvvm.ui.home.viewmodel.MainActivityViewModel;
import com.android.catfactappmvvm.ui.maps.MapActivity;
import com.android.catfactappmvvm.ui.personal.PersonalActivity;
import com.android.catfactappmvvm.util.LocationUtility;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    //Views
    private TextView textViewTitle;
    private EditText editTextAmount;
    private Button buttonSubmit;
    private RecyclerView recyclerView;

    private FactAdapter adapter;

    @Inject
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTitle = findViewById(R.id.text_view_title);
        editTextAmount = findViewById(R.id.edit_text_amount);
        buttonSubmit = findViewById(R.id.button_submit);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FactAdapter();
        recyclerView.setAdapter(adapter);

        setOnClickListeners();
        requestPermissions();
    }

    private void getFactList(int amount) {
        mainActivityViewModel.getAllFacts("cat", amount).observe(this, new Observer<List<Fact>>() {
            @Override
            public void onChanged(List<Fact> facts) {
                adapter.setFacts(facts);
            }
        });
    }

    private void setOnClickListeners() {
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt(editTextAmount.getText().toString());
                getFactList(amount);
            }
        });
    }

    private void requestPermissions() {
        if (LocationUtility.hasLocationPermissions(getApplicationContext())) {
            return;
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                    this,
                    "You need to accept location permissions to use this app.",
                    0,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            EasyPermissions.requestPermissions(
                    this,
                    "You need to accept location permissions to use this app.",
                    0,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        }
    }

    //TO SHOW ICON ON THE TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.go_to_personal_facts, menu);
        return true;
    }

    //TO HANDLE CLICKS ON THE TOOLBAR BUTTONS
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_to_personal_facts:
                Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
                startActivity(intent);
            case R.id.go_to_maps:
                Intent intentMaps = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intentMaps);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {}

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        } else {
            requestPermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}