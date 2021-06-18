package com.example.remote_joystick.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.remote_joystick.R;
import com.example.remote_joystick.model.FGPlayer;
import com.example.remote_joystick.view_Model.ViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

//    private FGPlayer fg;
    private androidx.constraintlayout.widget.ConstraintLayout mainLayout;
    private ViewModel viewModel;
    private EditText IP;
    private EditText port;
    private Button connectButton;
    private SeekBar rudderSeekBar;
    private SeekBar throttleSeekBar;
    private Joystick joystick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);
        IP = findViewById(R.id.editTextIP);
        port = findViewById(R.id.editTextPort);
        connectButton = findViewById(R.id.connectButton);
        rudderSeekBar = findViewById(R.id.rudderSeekBar);
        throttleSeekBar = findViewById(R.id.throttleSeekBar);

        connectButton.setOnClickListener(v -> {
            viewModel = new ViewModel();
            viewModel.connect(IP.getText().toString(),
                    Integer.parseInt(port.getText().toString()));
            try {
                viewModel.getFuturePool().get();
            } catch (Exception e) {
                // need to pop a message saying to try again to connect
                Snackbar.make(mainLayout, "Connect failed, please try again",
                       Snackbar.LENGTH_LONG)
                        .setAction("close", v1 -> {

                        })
                        .setActionTextColor(getResources().getColor(R.color.white))
                        .show();
            }
        });

        rudderSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double progress2 = (double)progress / 50;
                viewModel.setRudder(progress2 - 1);
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
                viewModel.setThrottle((double)progress / 100);
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