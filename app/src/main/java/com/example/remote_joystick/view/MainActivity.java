package com.example.remote_joystick.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.remote_joystick.R;

public class MainActivity extends AppCompatActivity {

    //private Joystick = new Joystick();
    SeekBar throttleSeekBar;
    SeekBar rudderSeekBar;
    EditText IP;
    EditText port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}