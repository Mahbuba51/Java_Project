package RestaurantDatabase;

import util.SocketWrapper;

import java.io.IOException;
import java.util.Scanner;

public class WriteThreadClient implements Runnable {

    private Thread thr;
    private SocketWrapper socketWrapper;
    String name;
    Object lock;

    public WriteThreadClient(SocketWrapper socketWrapper, String name, Object lock) {
        this.socketWrapper = socketWrapper;
        this.name = name;
        this.thr = new Thread(this);
        this.lock=lock;
        thr.start();
    }

    public void run() {
        try {
            boolean exit1 = true, exit2 = true, exit3 = true;
            int choice1, choice2, choice3;
            Scanner scanner = new Scanner(System.in);
            while (exit1) {
                System.out.println("Main Menu:");
                System.out.println("1) Search Restaurants");
                System.out.println("2) Search Food Items");
                System.out.println("3) Order Foods");
                System.out.println("4) Exit System");
                System.out.print("Enter option: ");

                choice1 = scanner.nextInt();
                scanner.nextLine();

                switch (choice1) {
                    case 1 -> {
                        int i=0;
                        exit2=true;
                        while (exit2) {
                            System.out.println("Restaurant Searching Options:");
                            System.out.println("1) By Name");
                            System.out.println("2) By Score");
                            System.out.println("3) By Category");
                            System.out.println("4) By Price");
                            System.out.println("5) By Zip Code");
                            System.out.println("6) Different Category Wise List of Restaurants");
                            System.out.println("7) Back to Main Menu");
                            System.out.print("Enter option: ");

                            choice2 = scanner.nextInt();
                            scanner.nextLine();
                            switch (choice2) {
                                case 1 -> {
                                    System.out.print("Enter Restaurant Name: ");
                                    String name = scanner.nextLine();

                                    socketWrapper.write(choice1 + "," + choice2 + "," + name);
                                }
                                case 2 -> {
                                    System.out.print("Enter Starting Score: ");
                                    double start = Double.parseDouble(scanner.nextLine());
                                    System.out.print("Enter Ending Score: ");
                                    double end = Double.parseDouble(scanner.nextLine());

                                    socketWrapper.write(choice1 +  "," + choice2 +  "," + start +  "," + end);
                                }
                                case 3 -> {
                                    System.out.print("Enter Category: ");
                                    String category = scanner.nextLine();

                                    socketWrapper.write(choice1 +  "," + choice2 +  "," + category);
                                }
                                case 4 -> {
                                    System.out.print("Enter Price: ");
                                    String price = scanner.nextLine();

                                    socketWrapper.write(choice1 +  "," + choice2 +  "," + price);
                                }
                                case 5 -> {
                                    System.out.print("Enter Zip Code: ");
                                    int zipCode = Integer.parseInt(scanner.nextLine());

                                    socketWrapper.write(choice1 +  "," + choice2 +  "," + zipCode);
                                }
                                case 6 -> socketWrapper.write(choice1 +  "," + choice2);
                                case 7 -> {
                                    exit2 = false;
                                    i=1;
                                }
                                default -> {
                                    System.out.println("Invalid Input");
                                    i=1;
                                }
                            }
                            if(i != 1){
                                synchronized (lock){
                                    lock.wait();
                                }
                            }
                        }
                    }
                    case 2 -> {
                        int i=0;
                        exit3=true;
                        while (exit3) {
                            System.out.println("Food Item Searching Options:");
                            System.out.println("1) By Name");
                            System.out.println("2) By Name in a Given Restaurant");
                            System.out.println("3) By Category");
                            System.out.println("4) By Category in a Given Restaurant");
                            System.out.println("5) By Price Range");
                            System.out.println("6) By Price Range in a Given Restaurant");
                            System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restaurant");
                            System.out.println("8) List of Restaurants and Total Food Item on the Menu");
                            System.out.println("9) Back to Main Menu");
                            System.out.print("Enter option: ");

                            choice3 = scanner.nextInt();
                            scanner.nextLine();
                            switch (choice3) {
                                case 1 -> {
                                    System.out.print("Enter Food Item Name: ");
                                    String name = scanner.nextLine();

                                    socketWrapper.write(choice1 +  "," + choice3 +  "," + name);
                                }
                                case 2 -> {
                                    System.out.print("Enter Food Item Name: ");
                                    String foodName = scanner.nextLine();
                                    System.out.print("Enter Restaurant Name: ");
                                    String resName = scanner.nextLine();

                                    socketWrapper.write(choice1 +  "," + choice3 +  "," + foodName +  "," + resName);
                                }
                                case 3 -> {
                                    System.out.print("Enter Category: ");
                                    String category = scanner.nextLine();

                                    socketWrapper.write(choice1 +  "," + choice3 +  "," + category);
                                }
                                case 4 -> {
                                    System.out.print("Enter Category: ");
                                    String cat = scanner.nextLine();
                                    System.out.print("Enter Restaurant Name: ");
                                    String Name = scanner.nextLine();

                                    socketWrapper.write(choice1 +  "," + choice3 +  "," + cat +  "," + Name);
                                }
                                case 5 -> {
                                    System.out.print("Enter Starting Price: ");
                                    double start = Double.parseDouble(scanner.nextLine());
                                    System.out.print("Enter Ending Price: ");
                                    double end = Double.parseDouble(scanner.nextLine());

                                    socketWrapper.write(choice1 +  "," + choice3 +  "," + start +  "," + end);
                                }
                                case 6 -> {
                                    System.out.print("Enter Starting Price: ");
                                    double priceStart = Double.parseDouble(scanner.nextLine());
                                    System.out.print("Enter Ending Price: ");
                                    double priceEnd = Double.parseDouble(scanner.nextLine());
                                    System.out.print("Enter Restaurant Name: ");
                                    String res = scanner.nextLine();

                                    socketWrapper.write(choice1 +  "," + choice3 +  "," + priceStart +  "," + priceEnd +  "," + res);
                                }
                                case 7 -> {
                                    System.out.print("Enter Restaurant Name: ");
                                    String restaurant = scanner.nextLine();

                                    socketWrapper.write(choice1 +  "," + choice3 +  "," + restaurant);
                                }
                                case 8 -> {
                                    socketWrapper.write(choice1 +  "," + choice3);
                                }
                                case 9 -> {
                                    exit3 = false;
                                    i=1;
                                }
                                default -> {
                                    System.out.println("Invalid Input");
                                    i=1;
                                }
                            }
                            if(i != 1){
                                synchronized (lock){
                                    lock.wait();
                                }
                            }

                        }
                    }
                    case 3 -> {
                        System.out.print("Enter Restaurant ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter the Name of the Food Item: ");
                        String foodName = scanner.nextLine();

                        socketWrapper.write(choice1 +"," + id + "," + foodName);
                        System.out.println("Order Placed!");
                    }

                    case 4 -> exit1 = false;
                    default -> System.out.println("Invalid Input");
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
