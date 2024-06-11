package RestaurantDatabase;

import util.SocketWrapper;

import java.util.Scanner;

public class Client {
    String type;
    Object lock=new Object();

    public String getType() {
        return type;
    }


    public Client(String serverAddress, int serverPort, String type) {
        try {
            this.type=type;
            System.out.print("Enter name of the client: ");
            Scanner scanner = new Scanner(System.in);
            String clientName = scanner.nextLine();
            SocketWrapper socketWrapper = new SocketWrapper(serverAddress, serverPort);
            socketWrapper.write("client," + clientName);
            new ReadThreadClient(socketWrapper, lock);
            new WriteThreadClient(socketWrapper, "Client", lock);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        new Client(serverAddress, serverPort, "Client");
    }
}
