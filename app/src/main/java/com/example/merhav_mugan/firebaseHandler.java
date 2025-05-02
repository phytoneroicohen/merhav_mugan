package com.example.merhav_mugan;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class firebaseHandler {
    private DatabaseReference dbRef;

    public firebaseHandler() {
        dbRef = FirebaseDatabase.getInstance().getReference("shelters");
    }

    // 🔸 Define the callback interface INSIDE this class
    public interface ShelterListCallback {
        void onResult(List<merhav_mugan> shelters);
        void onError(String error);
    }

    // 🔸 This method accepts a callback and loads the data
    public void getAllShelters(ShelterListCallback callback) {
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<merhav_mugan> list = new ArrayList<>();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    merhav_mugan shelter = snap.getValue(merhav_mugan.class);
                    if (shelter != null) list.add(shelter);
                }
                callback.onResult(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.getMessage());
            }
        });
    }
}
