package com.example.noreahhotelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HotelViewActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view_b);

        Button buttonBookNow = findViewById(R.id.buttonBookNow);
        buttonBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelViewActivityB.this, BookingActivity.class);
                startActivity(intent);
            }
        });
    }
}
