package com.example.noreahhotelbooking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;

public class BookingActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextName, editTextPhone, editTextEmail, editCheckinDate, editCheckoutDate;
    private Button buttonConfirmBooking, buttonUploadPayment;
    private ImageView imageViewPayment;
    private boolean isImageSelected = false; // Flag to check if image is selected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Initialize the EditText, Button, and ImageView fields
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editCheckinDate = findViewById(R.id.editChekindate);
        editCheckoutDate = findViewById(R.id.editChekoutdate);
        buttonConfirmBooking = findViewById(R.id.buttonConfirmBooking);
        buttonUploadPayment = findViewById(R.id.buttonUploadPayment);
        imageViewPayment = findViewById(R.id.imageViewPayment);

        // Set the onClickListener for the button
        buttonConfirmBooking.setOnClickListener(v -> {
            if (validateInput()) {
                // Add your booking logic here
                Toast.makeText(BookingActivity.this, "Booking Confirmed. Thanks for choosing us!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonUploadPayment.setOnClickListener(v -> openImageChooser());
    }

    private boolean validateInput() {
        // Check if the input field editTextName is empty after trimming whitespace
        if (editTextName.getText().toString().trim().isEmpty()) {
            // If it is empty, set an error message on the editTextName field
            editTextName.setError("Name is required");
            // Move the focus to the editTextName field so the user can correct the input
            editTextName.requestFocus();
            // If the input is not empty, the validation is successful
            return false;
        }

        if (editTextPhone.getText().toString().trim().isEmpty()) {
            editTextPhone.setError("Phone number is required");
            editTextPhone.requestFocus();
            return false;
        }

        if (editTextEmail.getText().toString().trim().isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return false;
        }

        if (editCheckinDate.getText().toString().trim().isEmpty()) {
            editCheckinDate.setError("Check-in date is required");
            editCheckinDate.requestFocus();
            return false;
        }

        if (editCheckoutDate.getText().toString().trim().isEmpty()) {
            editCheckoutDate.setError("Check-out date is required");
            editCheckoutDate.requestFocus();
            return false;
        }

        if (!isImageSelected) {
            Toast.makeText(this, "Payment image is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Add other validations if needed
        return true;
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Payment Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageViewPayment.setImageBitmap(bitmap);
                isImageSelected = true; // Set flag to true when image is selected
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
