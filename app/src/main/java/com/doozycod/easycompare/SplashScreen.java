package com.doozycod.easycompare;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashScreen extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {

        /* you don't have to override every property */

        //Customize Circular Reveal
//        Typeface custom_font = Typeface.createFromAsset(getAssets(), "font/timeburnernormal.ttf");


        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        configSplash.setBackgroundColor(R.color.windowBackground); //any color you want form colors.xml

        configSplash.setAnimCircularRevealDuration(1000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.ic_launcher); //or any other drawable
        configSplash.setAnimLogoSplashDuration(1000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Swing); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

        //Customize Title
        getSupportActionBar().hide();
        configSplash.setTitleSplash("Easy Compare!");
        configSplash.setTitleTextColor(R.color.textColorSecondary);
        configSplash.setTitleTextSize(28f); //float value
        configSplash.setAnimTitleDuration(1000);
        configSplash.setAnimTitleTechnique(Techniques.Wobble);

    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(SplashScreen.this, NavigationActivity.class));
        finish();
    }
}
