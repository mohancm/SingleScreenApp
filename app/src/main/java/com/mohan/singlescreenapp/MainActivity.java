package com.mohan.singlescreenapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView phone = findViewById(R.id.tv_phone);
        TextView address = findViewById(R.id.tv_address);
        TextView website = findViewById(R.id.tv_website);

        phone.setOnClickListener((View.OnClickListener) this);
        address.setOnClickListener((View.OnClickListener) this);
        website.setOnClickListener((View.OnClickListener) this);

        final RatingBar minimumRating = (RatingBar)findViewById(R.id.myRatingBar);
        minimumRating.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View view, MotionEvent event)
            {
                float touchPositionX = event.getX();
                float width = minimumRating.getWidth();
                float starsf = (touchPositionX / width) * 5.0f;
                int stars = (int)starsf + 1;
                minimumRating.setRating(stars);
                return true;
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_phone:
                switchToPhoneIntent();
                break;
            case R.id.tv_address:
                switchToMapsIntent();
                break;
            case R.id.tv_website:
                switchToWebIntent();
                break;
        }

    }
    private  void switchToPhoneIntent(){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +getString(R.string.phone_no)));
        startActivity(intent);
    }

    private void switchToMapsIntent(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:13.046087, 77.566732?z=15 m"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void switchToWebIntent() {
        Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.tholons.com/"));
        startActivity(i);
    }
}
