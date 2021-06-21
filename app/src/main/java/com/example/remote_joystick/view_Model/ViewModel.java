package com.example.remote_joystick.view_Model;

import com.example.remote_joystick.model.FGPlayer;

import java.util.concurrent.Future;

public class ViewModel {

    // field
    private FGPlayer fg;

    /**
     * constructor
     */
    public ViewModel() {
        this.fg = new FGPlayer();
    }

    /**
     * connects to the server
     * @param host server ip
     * @param port server port
     */
    public void connect(String host, int port) {
        this.fg.connect(host, port);
    }

    /**
     * returns future to keep the connection synced
     * @return future
     */
    public Future<?> getFuturePool() {
        return fg.getFuturePool();
    }

    /**
     * insert to the thread pool queue a task to change aileron
     * @param val value
     */
    public void setAileron(double val) {
        fg.insertTask("aileron", val);
    }

    /**
     * insert to the thread pool queue a task to change elevator
     * @param val value
     */
    public void setElevator(double val) {
        fg.insertTask("elevator", val);
    }

    /**
     * insert to the thread pool queue a task to change rudder
     * @param val value
     */
    public void setRudder(double val) {
        fg.insertTask("rudder", val);
    }

    /**
     * insert to the thread pool queue a task to change throttle
     * @param val value
     */
    public void setThrottle(double val) {
        fg.insertTask("throttle", val);
    }
}
