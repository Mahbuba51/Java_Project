package RestaurantDatabase;

import util.SocketWrapper;

import java.util.Scanner;

public class RestaurantAdmin {
    String type;

    public RestaurantAdmin(String serverAddress, int serverPort, String type) {
        try {
            this.type = type;
            System.out.print("Enter ID of the restaurant: ");
            Scanner scanner = new Scanner(System.in);
            String id = scanner.nextLine();
            SocketWrapper socketWrapper = new SocketWrapper(serverAddress, serverPort);
            socketWrapper.write("restaurant" +"," + id);
            new ReadThreadRestaurant(socketWrapper);
            //new WriteThreadRestaurant(socketWrapper, "Restaurant");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;

        new RestaurantAdmin(serverAddress, serverPort, "Restaurant");

    }
}

