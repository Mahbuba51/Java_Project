package RestaurantDatabase;

import util.SocketWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ReadThreadRestaurant implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    static List<Food> menu;
    int count = 0;
    HashMap<Food, Integer> orderCount;
    public ReadThreadRestaurant(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
        menu = new ArrayList<>();
        orderCount = new HashMap<>();
        this.thr = new Thread(this);
        thr.start();
    }

    public static Food isAvailable(String foodName) {
        for (Food food : menu) {
            if (food.getName().equals(foodName))
                return food;
        }
        return null;
    }

    @Override
    public void run() {
        try {
            Object o = socketWrapper.read();
            if (o instanceof Restaurant) {
                menu = ((Restaurant) o).getMenu();
                System.out.println("Got Menu");
                for(Food food: menu){
                    orderCount.put(food, 0);
                }
            }
            while (true) {
                Object obj = socketWrapper.read();
                if (obj instanceof String) {
                    String[] input = ((String) obj).split(",");
                    Food food=isAvailable(input[1]);
                    if (food != null) {
                        int i= orderCount.get(food);
                        orderCount.put(food, i+1);
                        count++;
                        System.out.println("1 new order has been placed.");
                        System.out.println("Food ordered: " + input[1]);
                        System.out.println("Total order: " + count);
                        Food food1;
                        Iterator<Food> itr = orderCount.keySet().iterator();
                        while (itr.hasNext()) {
                            food1 = itr.next();
                            int j=orderCount.get(food1);
                            if(j != 0){
                                System.out.println(food1.getName() + ": " + orderCount.get(food1));
                            }
                        }
                        System.out.println();

                    } else {
                        System.out.println("Didn't find an order");
                    }
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
