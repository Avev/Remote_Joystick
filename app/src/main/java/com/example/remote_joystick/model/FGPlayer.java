package com.example.remote_joystick.model;

import java.io.PrintWriter;
import java.lang.reflect.Executable;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FGPlayer {
    // field
    private Socket fg;
    private PrintWriter out;
    private ExecutorService pool;

    /**
     * constructor, creates a socket and connect to fg server and opens a threadpool
     * @param host server ip
     * @param port server port
     */
    public FGPlayer(String host, int port) {
        try {
            this.fg = new Socket(host, port);
            this.out = new PrintWriter(this.fg.getOutputStream(), true);
            this.pool = Executors.newFixedThreadPool(1);
        } catch (Exception e) {
            System.out.println("connective to FlightGear failed");
        }
    }

    public Socket getFG() {
        return this.fg;
    }

    public PrintWriter getOut() {
        return this.out;
    }

    public ExecutorService getPool() {
        return this.pool;
    }

    public void insertTask(String type, double value) {
        if (type.equals("aileron")) {
            this.pool.execute( () -> {
                this.out.print("set/controls/flight/aileron " + value + "\r\n");
                this.out.flush();
            });
        }
        if (type.equals("elevator")) {
            this.pool.execute( () -> {
                this.out.print("set/controls/flight/elevator " + value + "\r\n");
                this.out.flush();
            });
        }
        if (type.equals("rudder")) {
            this.pool.execute( () -> {
                this.out.print("set/controls/flight/rudder " + value + "\r\n");
                this.out.flush();
            });
        }
        if (type.equals("throttle")) {
            this.pool.execute( () -> {
                this.out.print("set/controls/engines/current-engine/throttle " + value + "\r\n");
                this.out.flush();
            });
        }
    }
}
