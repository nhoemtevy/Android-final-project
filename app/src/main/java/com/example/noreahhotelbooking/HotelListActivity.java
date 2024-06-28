package com.example.noreahhotelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class HotelListActivity extends AppCompatActivity {

    private ListView listViewHotels;
    private String[] hotels = {"Double Bed", "Single Bed", "Double VIP", "sweet Bed VIP", "Single Bed", "Single Bed Pro", "Double Air Conditioner", "Double Fans"};
    private int[] hotelPics = {
            R.drawable.double60, R.drawable.double50, R.drawable.doublesweet100,
            R.drawable.hotel1, R.drawable.single, R.drawable.hotel1,
            R.drawable.doublebed80, R.drawable.doublesweet100
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        listViewHotels = findViewById(R.id.listViewHotels);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hotels);
        listViewHotels.setAdapter(adapter);

        listViewHotels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedHotel = hotels[position];
                Intent intent;

                switch (selectedHotel) {
                    case "Hotel B":
                        intent = new Intent(HotelListActivity.this, HotelViewActivityB.class);
                        break;
                    default:
                        intent = new Intent(HotelListActivity.this, HotelDetailsActivity.class);
                        intent.putExtra("hotelName", selectedHotel);
                        intent.putExtra("hotelPicResId", hotelPics[position]);
                        break;
                }

                startActivity(intent);
            }
        });
    }
}
