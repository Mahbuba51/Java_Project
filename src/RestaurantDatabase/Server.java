
package RestaurantDatabase;

import util.SocketWrapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

    private ServerSocket serverSocket;
    HashMap<String, SocketWrapper> restaurantMap;
    static RestaurantDatabaseManager restaurantDatabaseManager = new RestaurantDatabaseManager();
    private static final String RESTAURANT = "F:\\L-1T-2\\TestJavaFX\\src\\RestaurantDatabase\\restaurant.txt";
    private static final String MENU = "F:\\L-1T-2\\TestJavaFX\\src\\RestaurantDatabase\\menu.txt";


    Server() {
        try {
            System.out.println("Loading files...");
            BufferedReader restaurantReader = new BufferedReader(new FileReader(RESTAURANT));
            while (true) {
                String line = restaurantReader.readLine();
                if (line == null) break;
                String[] array = line.split(",", -1);
                int id = Integer.parseInt(array[0]);
                String name = array[1];
                double score = Double.parseDouble(array[2]);
                String price = array[3];
                int zipCode = Integer.parseInt(array[4]);
                String category1 = array[5];
                String category2 = array[6];
                String category3 = array[7];
                Restaurant e = new Restaurant(id, name, score, price, zipCode, category1, category2, category3);
                restaurantDatabaseManager.addRestaurant(e);

            }
            restaurantReader.close();
            BufferedReader menuReader = new BufferedReader(new FileReader(MENU));
            while (true) {
                String line = menuReader.readLine();
                if (line == null) break;
                String[] array = line.split(",", -1);
                int id = Integer.parseInt(array[0]);
                String res = restaurantDatabaseManager.restaurantsSearchById(id);
                String category = array[1];
                String name = array[2];
                double price = Double.parseDouble(array[3]);
                Food food = new Food(id, category, name, price);
                restaurantDatabaseManager.addFood(res, food);
            }
            menuReader.close();
            System.out.println("Files loaded!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        restaurantMap = new HashMap<>();
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        String[] str;
        String name;
        try {
            name = (String) socketWrapper.read();
            str = name.split(",");

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (str[0].equals("restaurant")) {
            restaurantMap.put(str[1], socketWrapper);
            new ReadThreadServer(restaurantMap, socketWrapper);
            Restaurant restaurant = restaurantDatabaseManager.getRestaurant(Integer.parseInt(str[1]));
            if(restaurant != null){
                socketWrapper.write(restaurant);
            }

        }
        else
            new ReadThreadServer(restaurantMap, socketWrapper);

        //new WriteThreadServer(restaurantMap, socketWrapper, "Server");

    }

    public static void main(String[] args) {
        new Server();
    }
}
