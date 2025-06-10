package com.example.merhav_mugan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.ValueCallback;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Map extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap myMap;
    private List<merhav_mugan> Shelters;
    private double myLatitude, myLongitude;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    public double getMyLatitude() {
        return myLatitude;
    }

    public double getMyLongitude() {
        return myLongitude;
    }

    private boolean userAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialize FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        // Initialize the map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.id_map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    //פעולה שמתבצעת כשהמפה מוכנה ומבצעת הצגת מקלטים ומיקום
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        // בדיקה אם המשתמש צריך נגישות אם כן יוצגו רק הנגישים אם לא אז כולם יוצגו
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        long id = sharedPreferences.getLong("id", -1);
        if (id != -1) {
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

            dbRef.child("users").child(String.valueOf(id)).child("needAccessibility")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Boolean needAccessibility = snapshot.getValue(Boolean.class);
                            if (needAccessibility != null) {
                                userAccess = needAccessibility;
                                ;
                            } else {
                                Toast.makeText(Map.this, "Value not found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Map.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(Map.this, "User ID not found", Toast.LENGTH_SHORT).show();
        }

        // אם יש הרשאה קורא ל getlastlocation
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            myMap.setMyLocationEnabled(true);
            getLastLocation(); // פעולה להצגת מיקום משתמש
        // אם אין אישור מבקש
        } else {
            requestLocationPermission();
        }
        // שימוש ב firebasehandler כדי לקבל נתונים של מרחבים מוגנים מ firebase
        firebaseHandler handler = new firebaseHandler();
        handler.getAllShelters(new firebaseHandler.ShelterListCallback() {

            @Override
            public void onResult(List<merhav_mugan> mugans) {

                Shelters = mugans;
                if (userAccess) {
                    for (int i = 0; i < mugans.size(); i++) {
                        LatLng coordinate = new LatLng(mugans.get(i).getlatitude(), mugans.get(i).getLongitude());
                        if (mugans.get(i).isAccessible()) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(coordinate)
                                    .title(mugans.get(i).toString()));
                        }
                    }
                } else {
                    for (int i = 0; i < mugans.size(); i++) {
                        LatLng coordinate = new LatLng(mugans.get(i).getlatitude(), mugans.get(i).getLongitude());
                        googleMap.addMarker(new MarkerOptions()
                                .position(coordinate)
                                .title(mugans.get(i).toString()));

                    }


                }

            }

            @Override
            public void onError(String error) {
                Toast.makeText(Map.this, "Failed: " + error, Toast.LENGTH_SHORT).show();
            }
        });

    }

    // מבקשת מהמשתמש הרשאה לשימוש במיקום
    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE);
    }
// פעולה שמביאה את המיקום האחרון של המשתמש ומציגה אותו על המפה.

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                myLatitude = currentLatLng.latitude;
                myLongitude = currentLatLng.longitude;
                myMap.addMarker(new MarkerOptions().position(currentLatLng).title("You are here"));
                myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
            } else {
                Toast.makeText(Map.this, "שגיאה בהצגת מיקום", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //פעולה שבודקת את תוצאת הבקשה למיקום
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // אם אושר
                getLastLocation();
            } else {
                // אם נדחה - הודעה
                Toast.makeText(Map.this, "Location permissions denied, please allow location permission", Toast.LENGTH_SHORT).show();
            }
        }
    }


    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_DB) {
            Intent intent = new Intent(this, DB_Main.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_MAP) {
            Intent intent = new Intent(this, Map.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_Login) {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}