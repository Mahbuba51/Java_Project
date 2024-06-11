# Food Order Delivery Service

This project is a console-based food order delivery service implemented in Java. It uses concepts of Java threading and networking to create an interactive platform for clients and restaurants. 

## Features

- **Server**: Maintains all data and connections.
- **Restaurants**: Can log in, wait for orders, and view incoming orders in real-time.
- **Clients**: Can log in, search for food and restaurants based on various criteria, and place orders.
- **Real-time Order Updates**: Restaurants receive orders immediately without needing to refresh the console.

## How It Works

### Server
The server is the backbone of the application, handling all client and restaurant connections, maintaining a list of available restaurants, and managing order processing.

### Restaurants
- **Login**: Restaurants can log in to the system using their credentials.
- **Waiting for Orders**: Once logged in, restaurants wait for orders from clients.
- **Receiving Orders**: Orders from clients are received in real-time and displayed in the console.

### Clients
- **Login**: Clients can log in to the system using their credentials.
- **Search**: Clients can search for food and restaurants based on various criteria such as cuisine type, restaurant ratings, or specific dish names.
- **Place Orders**: Clients can place orders which are then sent to the respective restaurants in real-time.

## Setup and Running the Application

### Prerequisites
- Java Development Kit (JDK) installed on your system.

### Running the Server
1. Navigate to the server directory in your terminal.
2. Compile the server code:
   javac Server.java
3. Run the server:
   java Server

### Running the Restaurant Console
1. Navigate to the restaurant directory in your terminal.
2. Compile the restaurant console code:
   javac RestaurantAdmin.java
3. Run the restaurant console
   java RestaurantAdmin

### Running the Client Console
1. Navigate to the client directory in your terminal.
2. Compile the client console code:
   javac Client.java
3. Run the client console:
   java Client

## Technologies Used

- **Java**: Core programming language used.
- **Threading**: For managing concurrent connections and real-time updates.
- **Networking**: For handling communication between server, clients, and restaurants.

## Contributions

Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to create a pull request or open an issue.
```

This version removes the licensing section. Feel free to add any additional details specific to your project as needed!
