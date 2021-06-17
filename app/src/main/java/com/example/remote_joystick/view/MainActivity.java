package com.example.remote_joystick.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.remote_joystick.R;
import com.example.remote_joystick.model.FGPlayer;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private FGPlayer fg;
    private EditText IP;
    private EditText port;
    private Button connectButton;
    private SeekBar throttleSeekBar;
    private SeekBar rudderSeekBar;
    private Joystick joystick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IP = findViewById(R.id.editTextIP);
        port = findViewById(R.id.editTextPort);
        connectButton = findViewById(R.id.connectButton);
        throttleSeekBar = findViewById(R.id.rudderSeekBar);
        rudderSeekBar = findViewById(R.id.throttleSeekBar);

        connectButton.setOnClickListener(v -> {
            fg = new FGPlayer();
            fg.connect(IP.getText().toString(),
                    Integer.parseInt(port.getText().toString()));
            try {
                fg.getFuturePool().get();
            } catch (Exception e) {
                // need to pop a message saying to try again to connect
                System.out.println("socket failed");
            }
        });

        rudderSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress -= 1;
                fg.insertTask("rudder", (double)progress / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        throttleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fg.insertTask("throttle", (double)progress / 100);
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