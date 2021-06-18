package com.example.remote_joystick.view_Model;

import com.example.remote_joystick.model.FGPlayer;

import java.util.concurrent.Future;

public class ViewModel {

    private FGPlayer fg;

    public ViewModel() {
        this.fg = new FGPlayer();
    }

    public void connect(String host, int port) {
        this.fg.connect(host, port);
    }

    public Future<?> getFuturePool() {
        return fg.getFuturePool();
    }

    public void setAileron(double val) {
        fg.insertTask("aileron", val);
    }

    public void setElevator(double val) {
        fg.insertTask("elevator", val);
    }

    public void setRudder(double val) {
        fg.insertTask("rudder", val);
    }

    public void setThrottle(double val) {
        fg.insertTask("throttle", val);
    }
}
