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


    public firebaseHandler() {      // constructor

        dbRef = FirebaseDatabase.getInstance().getReference("shelters");
    }
    // ממשק שמוגדר כדי לאפשר החזרת תוצאה בצורה אסינכרונית (callback)
    public interface ShelterListCallback {
        void onResult(List<merhav_mugan> shelters);
        void onError(String error);
    }
    // פעולה שמביאה את כל המרחבים המוגנים מהמסד
    public void getAllShelters(ShelterListCallback callback) {

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<merhav_mugan> list = new ArrayList<>(); // רשימה ריקה של מרחבים
                // לולאה שעוברת על כל הילדים של "shelters"
                for (DataSnapshot snap : snapshot.getChildren()) {
                    // ממיר כל אובייקט מהמסד למחלקת merhav_mugan
                    merhav_mugan shelter = snap.getValue(merhav_mugan.class);
                    if (shelter != null) list.add(shelter);
                }
                callback.onResult(list);  // שולח את הרשימה חזרה דרך הממשק
            }
            @Override
            // במקרה של שגיאה בגישה למסד – מחזיר הודעת שגיאה
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.getMessage());
            }
        });
    }

}
