package RestaurantDatabase;

import util.SocketWrapper;

import java.io.IOException;

public class WriteThreadRestaurant implements Runnable{
    private Thread thr;
    private SocketWrapper socketWrapper;
    String name;

    public WriteThreadRestaurant(SocketWrapper socketWrapper, String name) {
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        this.name=name;
        thr.start();
    }
    @Override
    public void run() {
        try {
            while (true) {
                String s = (String) socketWrapper.read();
                System.out.println("Received: " + s);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
