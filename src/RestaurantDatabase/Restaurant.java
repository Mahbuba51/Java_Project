package RestaurantDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Restaurant implements Serializable {
    private int id;
    private String name;
    private double score;
    private String price;
    private int zipCode;
    private String[] categories = new String[3];
    private int foodItems = 0;
    private List<Food> menu = new ArrayList<>();

    public List<Food> getMenu() {
        return menu;
    }

    public Restaurant(int id, String name, double score, String price, int zipCode, String category1, String category2, String category3) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.zipCode = zipCode;
        this.categories[0] = category1;
        this.categories[1] = category2;
        this.categories[2] = category3;
    }

    public int getFoodItems() {
        return foodItems;
    }


    public void addFood(Food food) {
        int searchIndex = 0;
        for (Food food1 : menu) {
            if (food1.getName().equals(food.getName()) && food1.getCategory().equals(food.getCategory())) {
                searchIndex = 1;
                break;
            }
        }
        if (searchIndex == 0) {
            menu.add(food);
            foodItems++;
        } else
            System.out.println("A food with this name already exists.");
    }

    public List<Food> searchFoodByName(String name) {
        List<Food> foodList = new ArrayList<>();
        for (Food food : menu) {
            if (food.getName().toLowerCase().contains(name.toLowerCase())) {
                foodList.add(food);
            }
        }
        return foodList;
    }

    public List<Food> searchFoodByCategory(String category) {
        List<Food> foodList = new ArrayList<>();
        for (Food food : menu) {
            if (food.getCategory().toLowerCase().contains(category.toLowerCase())) {
                foodList.add(food);
            }
        }
        return foodList;
    }

    public List<Food> searchFoodByPrice(double start, double end) {
        List<Food> foodList = new ArrayList<>();
        for (Food food : menu) {
            if (food.getPrice() >= start && food.getPrice() <= end) {
                foodList.add(food);
            }
        }
        return foodList;
    }

    public List<Food> costliestFoodItems() {
        List<Food> foodList=new ArrayList<>();
        double costliest = 0;
        for (Food food : menu) {
            if (food.getPrice() > costliest) {
                costliest = food.getPrice();
            }
        }
        for (Food food : menu) {
            if (food.getPrice() == costliest) {
                foodList.add(food);
            }
        }
        return foodList;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getScore() {
        return this.score;
    }

    public String getPrice() {
        return this.price;
    }

    public int getZipCode() {
        return this.zipCode;
    }

    public String[] getCategories() {
        return this.categories;
    }

    public void showDetails() {
        System.out.println("Restaurant ID: " + id);
        System.out.println("Restaurant Name: " + name);
        System.out.println("Restaurant Score: " + score);
        System.out.println("Restaurant Price: " + price);
        System.out.println("Restaurant ZipCode: " + zipCode);
        System.out.println("Category ");
        for (String cat : categories) {
            if (cat != null) {
                System.out.print(cat + "\t");
            }
        }
        System.out.println();
    }

    public boolean isAvailable(String foodName) {
        for(Food food: menu){
            if(food.getName().equals(foodName)){
                return true;
            }
        }
        return false;
    }
}
