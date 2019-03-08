package com.doozycod.easycompare;

import android.app.Activity;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scan extends Activity implements ZXingScannerView.ResultHandler {
    //    Initialising
    private ZXingScannerView mScannerView;

    String barcode, product_name, product_img, url_1, url_2, url_3, price_one, price_two, price_three;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
//      Fetching Data Through DBHelper Constructor

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
        mScannerView.setAutoFocus(true);
    }

    //      Predefined Method onResume
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    //      Predefined Method onPause
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    //      Predefined method of ZxingScanner to Receive the Result of Barcode
    @Override
    public void handleResult(Result rawResult) {


//        String for query
        String barcode = rawResult.toString().replaceAll("  ", "");


//          Creating Bundle object to get the Parsable data
        Bundle bundle = new Bundle();

//            Creating an Intent Object to Start Activity and Parse the Data to another activity
//        Intent intent_pass_productInfo = new Intent(BarCodeScanActivity.this, ProductInfoActivity.class);

//          Adding bundle object to intent Object to using putExtras to get the data in Another Activity
//        intent_pass_productInfo.putExtras(bundle);
//          Starting Another Activity using StartActvity and Intent Object
//        startActivity(intent_pass_productInfo);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


}
