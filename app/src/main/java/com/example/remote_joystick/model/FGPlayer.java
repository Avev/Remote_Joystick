package com.example.remote_joystick.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Executable;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FGPlayer {
    // field
    private Socket fg;
    private PrintWriter out;
    private ExecutorService pool;
    Future<?> futurePool;

    /**
     * constructor
     */
    public FGPlayer() {
        this.pool = Executors.newFixedThreadPool(1);
    }

    public void connect(String host, int port) {
        this.futurePool = this.pool.submit(() -> {
            this.fg = new Socket(host, port);
            this.out = new PrintWriter(this.fg.getOutputStream(), true);
            return null;
        });
    }

    public Future<?> getFuturePool() {
        return this.futurePool;
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
