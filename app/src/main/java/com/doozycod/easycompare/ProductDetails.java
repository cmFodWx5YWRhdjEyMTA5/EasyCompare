package com.doozycod.easycompare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ProductDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getSupportActionBar().hide();
    }
}
