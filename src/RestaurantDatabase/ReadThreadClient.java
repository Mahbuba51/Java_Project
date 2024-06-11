package RestaurantDatabase;

import util.SocketWrapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    Object lock;

    public ReadThreadClient(SocketWrapper socketWrapper, Object lock) {
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        this.lock=lock;
        thr.start();
    }

    public void run() {
        try {
            while (true) {

                List<Object> list = (List<Object>) socketWrapper.read();
                if(list.isEmpty()){
                    System.out.println("Not found");
                }
                else if (list.get(0) instanceof Food) {
                    for (Object food : list) {
                        ((Food) food).showDetails();
                    }
                } else if (list.get(0) instanceof Restaurant) {
                    for (Object restaurant : list) {
                        ((Restaurant) restaurant).showDetails();
                    }
                } else if (list.get(0) instanceof String) {
                    for(Object str: list){
                        System.out.println(str);
                    }
                } else
                    System.out.println("Not found");
                synchronized (lock){
                    lock.notify();
                }
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
