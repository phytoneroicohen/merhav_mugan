package com.example.merhav_mugan;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myHolder extends RecyclerView.ViewHolder {

    TextView idView,latitudeView,longitudeView,accessibleView,quantityView;
    public myHolder(@NonNull View itemView){
        super(itemView);
        idView=itemView.findViewById(R.id.itemID);
        latitudeView=itemView.findViewById(R.id.itemLatitude);
        longitudeView=itemView.findViewById(R.id.itemLongitude);
        accessibleView=itemView.findViewById(R.id.itemAccessible);
        quantityView=itemView.findViewById(R.id.itemQuantity);


    }
}


