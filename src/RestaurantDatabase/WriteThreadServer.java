package RestaurantDatabase;

import util.SocketWrapper;

import java.util.HashMap;
import java.util.Scanner;

public class WriteThreadServer implements Runnable {

    private final SocketWrapper socketWrapper;
    private Thread thr;
    String name;
    public HashMap<String, SocketWrapper> map;

    public WriteThreadServer(HashMap<String, SocketWrapper> map, SocketWrapper socketWrapper, String name) {
        this.map = map;
        this.socketWrapper = socketWrapper;
        this.name = name;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
