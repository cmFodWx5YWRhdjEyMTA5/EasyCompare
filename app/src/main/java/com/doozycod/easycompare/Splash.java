package com.doozycod.easycompare;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    final int DELAY = 1500;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = findViewById(R.id.text);
        getSupportActionBar().hide();
        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(new AlphaAnimation(0.0F, 1.0F));
        animation.addAnimation(new ScaleAnimation(0.0f, 1, 0.0f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)); // Change args as desired
        animation.setDuration(1500);

        textView.startAnimation(animation);

        new CountDownTimer(2000, 800) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {


                Intent i = new Intent(Splash.this, NavigationActivity.class);
                startActivity(i);
                finish();
            }
        }.start();
    }
}
