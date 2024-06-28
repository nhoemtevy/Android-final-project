package com.example.noreahhotelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HotelDetailsActivity extends AppCompatActivity {

    private TextView textViewHotelName, textViewHotelDescription;
    private ImageView imageViewHotelPic;
    private Button buttonBookNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        textViewHotelName = findViewById(R.id.textViewHotelName);
        textViewHotelDescription = findViewById(R.id.textViewHotelDescription);
        imageViewHotelPic = findViewById(R.id.imageViewHotel);
        buttonBookNow = findViewById(R.id.buttonBookNow);

        String hotelName = getIntent().getStringExtra("hotelName");
        int hotelPicResId = getIntent().getIntExtra("hotelPicResId", 0);

        textViewHotelName.setText(hotelName);
        textViewHotelDescription.setText("  Price 50$/Days " + hotelName);
        if (hotelPicResId != 0) {
            imageViewHotelPic.setImageResource(hotelPicResId);
        }

        buttonBookNow.setOnClickListener(v -> {
            Intent intent = new Intent(HotelDetailsActivity.this, BookingActivity.class);
            intent.putExtra("hotelName", hotelName);
            startActivity(intent);
        });
    }
}
