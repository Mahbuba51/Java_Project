package RestaurantDatabase;

import java.io.Serializable;

class Food implements Serializable {
    private int restaurantId;
    private String category;
    private String name;
    private double price;

    public Food(int restaurantId, String category, String name, double price) {
        this.restaurantId = restaurantId;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Food() {

    }

    public String getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void showDetails() {
        System.out.println("Food Name: " + name);
        System.out.println("Restaurant ID: " + restaurantId);
        System.out.println("Food Category: " + category);
        System.out.println("Food Price: " + price);
    }
}
