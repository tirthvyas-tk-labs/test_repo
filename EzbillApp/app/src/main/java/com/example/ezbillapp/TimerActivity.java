package com.example.ezbillapp;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {


    BluetoothAdapter mBluetoothAdapter;
    Button button, connect_btn, unpair, save_btn, start_elec;

    TextView timerText;
    Button stopStartButton;

    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;

    boolean timerStarted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);


        start_elec = findViewById(R.id.start_elec);

        timerText = (TextView) findViewById(R.id.timerText);
        stopStartButton = (Button) findViewById(R.id.startStopButton);

        save_btn = findViewById(R.id.save_button);

        timer = new Timer();


        start_elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(getApplicationContext(), "Now you can start using electricity....  ", Toast.LENGTH_LONG);
                toast.show();
                timerText.setVisibility(View.VISIBLE);
                stopStartButton.setVisibility(View.VISIBLE);

            }
        });



    }


    public void resetTapped(View view)
    {
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("Reset Timer");
        resetAlert.setMessage("Are you sure you want to reset the timer?");
        resetAlert.setPositiveButton("Reset", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if(timerTask != null)
                {
                    timerTask.cancel();
                    setButtonUI("START");
                    time = 0.0;
                    timerStarted = false;
                    timerText.setText(formatTime(0,0,0));

                }
            }
        });

        resetAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                //do nothing
            }
        });

        resetAlert.show();

    }

    public void startStopTapped(View view)
    {
        if(timerStarted == false)
        {
            timerStarted = true;
            setButtonUI("STOP");

            startTimer();
        }
        else
        {
            timerStarted = false;
            setButtonUI("START");

            timerTask.cancel();
        }
    }

    private void setButtonUI(String start)
    {
        stopStartButton.setText(start);
        //stopStartButton.setTextColor(ContextCompat.getColor(MainActivity.this));
    }

    private void startTimer()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 0 ,1000);

    }


    private String getTimerText()
    {

        final String total_timee = time.toString();
        System.out.println("Hola============================" + time);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<String, Object> Users = new HashMap<>();


                Users.put("Electricity", total_timee);

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                db.collection("Users").document(uid).update(Users).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(TimerActivity.this, "Name Added Successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                Intent intent=new Intent(TimerActivity.this, ElectricityActivity.class);
                intent.putExtra("message",total_timee);
                startActivity(intent);

            }
        });


        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        return formatTime(seconds, minutes, hours);


    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        String sec = String.format("%02d",seconds);
        String min = String.format("%02d",minutes);
        String hour = String.format("%02d",hours);

        String total_time = hour + ":" + min + ":" + sec;


        return total_time;
        //String.format("%02d",hours) + " : " + String.format("%02d",minutes) + " : " + String.format("%02d",seconds);
    }

    //================================================================================================

    //================================================================================================
    //================================================================================================    //================================================================================================
    //================================================================================================
    //================================================================================================
    //================================================================================================
    //================================================================================================




}