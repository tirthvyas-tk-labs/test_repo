package com.example.ezbillapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;


    //adding animations
    Animation topAnim, botAnim;

    ImageView image;
    TextView main, sub;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide the status bar basically
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);



        //Call Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_section);
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bot_sect);


        // Texts
        image = findViewById(R.id.imageView);
        main = findViewById(R.id.textView);
        sub = findViewById(R.id.textView2);


        image.setAnimation(topAnim);
        main.setAnimation(botAnim);
        sub.setAnimation(botAnim);


        //call splash screen under delay

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,LoginActivity.class);
                startActivity(intent);
                // it will remove the activity of the splash screen.
                finish();
            }
        }, SPLASH_SCREEN);

    }
}