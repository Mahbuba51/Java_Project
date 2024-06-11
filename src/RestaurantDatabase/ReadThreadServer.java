package RestaurantDatabase;

import util.SocketWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    HashMap<String , SocketWrapper> map;

    public ReadThreadServer(HashMap<String, SocketWrapper> map, SocketWrapper socketWrapper) {
        this.map=map;
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            List<Restaurant> restaurantList;
            List<Food> foodList;
            while (true) {
                String str= (String) socketWrapper.read();
                String [] input = str.split(",");
                switch (input[0]) {
                    case "1" -> {
                        switch (input[1]) {
                            case "1" -> {
                                restaurantList = Server.restaurantDatabaseManager.restaurantSearchByName(input[2]);
                                socketWrapper.write(restaurantList);
                            }
                            case "2" -> {
                                restaurantList = Server.restaurantDatabaseManager.restaurantSearchByScore(Double.parseDouble(input[2]), Double.parseDouble(input[3]));
                                socketWrapper.write(restaurantList);
                            }
                            case "3" -> {
                                restaurantList = Server.restaurantDatabaseManager.restaurantSearchByCategory(input[2]);
                                socketWrapper.write(restaurantList);
                            }
                            case "4" -> {
                                restaurantList = Server.restaurantDatabaseManager.restaurantSearchByPrice(input[2]);
                                socketWrapper.write(restaurantList);
                            }
                            case "5" -> {
                                restaurantList = Server.restaurantDatabaseManager.restaurantSearchByZipCode(Integer.parseInt(input[2]));
                                socketWrapper.write(restaurantList);
                            }
                            case "6" -> {
                                List<String> list=Server.restaurantDatabaseManager.restaurantCategoryWiseList();
                                socketWrapper.write(list);
                            }
                            default -> {
                            }
                        }
                    }
                    case "2" -> {
                        switch (input[1]) {
                            case "1" -> {
                                foodList = Server.restaurantDatabaseManager.foodSearchByName(input[2]);
                                socketWrapper.write(foodList);
                            }
                            case "2" -> {
                                foodList = Server.restaurantDatabaseManager.foodNameSearchByRestaurant(input[2], input[3]);
                                socketWrapper.write(foodList);
                            }
                            case "3" -> {
                                foodList = Server.restaurantDatabaseManager.foodSearchByCategory(input[2]);
                                socketWrapper.write(foodList);
                            }
                            case "4" -> {
                                foodList = Server.restaurantDatabaseManager.foodCategorySearchByRestaurant(input[2], input[3]);
                                socketWrapper.write(foodList);
                            }
                            case "5" -> {
                                foodList = Server.restaurantDatabaseManager.foodSearchByPrice(Double.parseDouble(input[2]), Double.parseDouble(input[3]));
                                socketWrapper.write(foodList);
                            }
                            case "6" -> {
                                foodList = Server.restaurantDatabaseManager.foodPriceSearchByRestaurant(Double.parseDouble(input[2]), Double.parseDouble(input[3]), input[4]);
                                socketWrapper.write(foodList);
                            }
                            case "7" -> {
                                foodList = Server.restaurantDatabaseManager.costliestFoodItems(input[2]);
                                socketWrapper.write(foodList);
                            }
                            case "8" -> {
                                List<String > list = Server.restaurantDatabaseManager.listOfRestaurants();
                                socketWrapper.write(list);
                            }
                            default -> {
                            }
                        }
                    }
                    case "3" -> {
                        SocketWrapper val = map.get(input[1]);
                        if(val != null){
                            val.write(input[1] + "," + input[2]);
                        }
                    }
                    default -> {
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
