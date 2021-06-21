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

    // field
    private androidx.constraintlayout.widget.ConstraintLayout mainLayout;
    private ViewModel viewModel;
    private EditText IP;
    private EditText port;
    private Button connectButton;
    private SeekBar rudderSeekBar;
    private SeekBar throttleSeekBar;
    private Joystick joystick;
    public static boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // field initialized
        isConnected = false;
        viewModel = new ViewModel();
        mainLayout = findViewById(R.id.mainLayout);
        IP = findViewById(R.id.editTextIP);
        port = findViewById(R.id.editTextPort);
        connectButton = findViewById(R.id.connectButton);
        rudderSeekBar = findViewById(R.id.rudderSeekBar);
        throttleSeekBar = findViewById(R.id.throttleSeekBar);
        joystick = findViewById(R.id.joystick);

        // when connect button is used
        connectButton.setOnClickListener(v -> {
            String IPString = IP.getText().toString();
            String portString = port.getText().toString();

            // if both text fields are not empty
            if (!IPString.isEmpty() && !portString.isEmpty()) {
                viewModel.connect(IPString,
                        Integer.parseInt(portString));
                try {
                    viewModel.getFuturePool().get();

                    // gets here only if connecting succeeded
                    isConnected = true;

                    // if failed to connect shows a failed message in a snackbar
                } catch (Exception e) {
                    Snackbar.make(mainLayout, "Connect failed, please try again",
                            Snackbar.LENGTH_LONG)
                            .setAction("close", v1 -> {

                            })
                            .setActionTextColor(getResources().getColor(R.color.white))
                            .show();
                }
            }

            // if one of the fields is empty shows a message in snackbar
            else {
                Snackbar.make(mainLayout, "IP/Port field is empty",
                        Snackbar.LENGTH_LONG)
                        .setAction("close", v1 -> {

                        })
                        .setActionTextColor(getResources().getColor(R.color.white))
                        .show();
            }
        });

        // when joystick is used
        joystick.setOnTouchListener((v, event) -> {
            joystick.onMove = (a, e) -> {
                viewModel.setAileron(a);
                viewModel.setElevator(e);
            };
            return false;
        });

        // when rudder seekbar is used
        rudderSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double progress2 = (double)progress / 50;
                if (isConnected) {
                    viewModel.setRudder(progress2 - 1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // when throttle seekbar is used
        throttleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (isConnected) {
                    viewModel.setThrottle((double)progress / 100);
                }
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