package com.example.remote_joystick.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.remote_joystick.R;
import com.example.remote_joystick.model.FGPlayer;

public class MainActivity extends AppCompatActivity {

    private FGPlayer fg;
    private EditText IP;
    private EditText port;
    private SeekBar throttleSeekBar;
    private SeekBar rudderSeekBar;
    private Joystick joystick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IP = findViewById(R.id.editTextIP);
        port = findViewById(R.id.editTextPort);
        throttleSeekBar = findViewById(R.id.rudderSeekBar);
        rudderSeekBar = findViewById(R.id.throttleSeekBar);


        throttleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}