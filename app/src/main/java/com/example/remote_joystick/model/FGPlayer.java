package com.example.remote_joystick.model;

import java.io.PrintWriter;
import java.net.Socket;
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

    /**
     * connects to the server
     * @param host server ip
     * @param port server port
     */
    public void connect(String host, int port) {
        this.futurePool = this.pool.submit(() -> {
            this.fg = new Socket(host, port);
            this.out = new PrintWriter(this.fg.getOutputStream(), true);
            return null;
        });
    }

    /**
     * returns future to keep the connection synced
     * @return future
     */
    public Future<?> getFuturePool() {
        return this.futurePool;
    }

    /**
     * insert the requested task to a threadpool queue
     * @param type what to change for example: "aileron"
     * @param value the value of the type
     */
    public void insertTask(String type, double value) {
        if (type.equals("aileron")) {
            this.pool.execute( () -> {
                this.out.print("set /controls/flight/aileron " + value + "\r\n");
                this.out.flush();
            });
        }
        if (type.equals("elevator")) {
            this.pool.execute( () -> {
                this.out.print("set /controls/flight/elevator " + value + "\r\n");
                this.out.flush();
            });
        }
        if (type.equals("rudder")) {
            this.pool.execute( () -> {
                this.out.print("set /controls/flight/rudder " + value + "\r\n");
                this.out.flush();
            });
        }
        if (type.equals("throttle")) {
            this.pool.execute( () -> {
                this.out.print("set /controls/engines/current-engine/throttle " + value + "\r\n");
                this.out.flush();
            });
        }
    }
}
