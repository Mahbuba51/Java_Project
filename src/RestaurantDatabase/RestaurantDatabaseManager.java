package RestaurantDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class RestaurantDatabaseManager {
    private List<Restaurant> restaurants = new ArrayList<>();
    private HashMap<String, List<Restaurant>> categories = new HashMap<>();

    public Restaurant getRestaurant(int id){
        Restaurant restaurant = null;
        for (Restaurant r: restaurants){
            if(id == r.getId()){
                restaurant=r;
            }
        }
        return restaurant;
    }
    public void addRestaurant(Restaurant restaurant) {
        int searchIndex = 0;
        for (Restaurant restaurant1 : restaurants) {
            if (restaurant1.getName().equals(restaurant.getName())) {
                searchIndex = 1;
                break;
            }
        }
        if (searchIndex == 0) {
            restaurants.add(restaurant);
            for (String category : restaurant.getCategories()) {
                if (!categories.containsKey(category)) {
                    categories.put(category, new ArrayList<>());
                }
                categories.get(category).add(restaurant);
            }
        } else
            System.out.println("A restaurant with this name already exists.");
    }

    public String restaurantsSearchById(int id) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == id) {
                return restaurant.getName();
            }
        }
        return null;
    }

    public Restaurant SearchByName(String str) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().toLowerCase().contains(str.toLowerCase())) {
                return restaurant;
            }
        }
        return null;
    }

    public List<Restaurant> restaurantSearchByName(String str) {
        List<Restaurant> restaurantList=new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().toLowerCase().contains(str.toLowerCase())) {
                restaurantList.add(restaurant);
            }
        }
            return restaurantList;
    }

    public List<Restaurant> restaurantSearchByScore(double start, double end) {
        List<Restaurant> restaurantList=new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getScore() >= start && restaurant.getScore() <= end) {
                restaurantList.add(restaurant);
            }
        }
        return restaurantList;
    }

    public List<Restaurant> restaurantSearchByCategory(String str) {
        List<Restaurant> restaurantList=new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            for (String cat : restaurant.getCategories()) {
                if (cat.toLowerCase().contains(str.toLowerCase())) {
                    restaurantList.add(restaurant);
                }
            }
        }
        return restaurantList;
    }

    public List<Restaurant> restaurantSearchByPrice(String str) {
        List<Restaurant> restaurantList=new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getPrice().equalsIgnoreCase(str)) {
                restaurantList.add(restaurant);
            }
        }
        return restaurantList;
    }

    public List<Restaurant> restaurantSearchByZipCode(int zipCode) {
        List<Restaurant> restaurantList=new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getZipCode() == zipCode) {
                restaurantList.add(restaurant);
            }
        }
        return restaurantList;
    }

    public List<String> restaurantCategoryWiseList() {
        List<String > list = new ArrayList<>();
        for (String category : categories.keySet()) {
            if (!category.isEmpty()) {
                list.add("Category: " + category);
                for (Restaurant restaurant : categories.get(category)) {
                    list.add("- " + restaurant.getName());
                }
            }
        }
        return list;
    }

    public List<Food> foodSearchByName(String name) {
        List<Food> foodList=new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            foodList.addAll(restaurant.searchFoodByName(name));
        }
        return foodList;
    }

    public List<Food> foodNameSearchByRestaurant(String food, String restaurant) {
        int searchIndex = 0;
        List<Food> foodList=new ArrayList<>();
        for (Restaurant res : restaurants) {
            if (res.getName().equalsIgnoreCase(restaurant)) {
                searchIndex =1;
                foodList = res.searchFoodByName(food);

                break;
            }
        }
        if (searchIndex == 0) {
            System.out.println("No such restaurant with this name.");
        }
        return foodList;
    }

    public List<Food> foodSearchByCategory(String cat) {
        List<Food> foodList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            foodList.addAll(restaurant.searchFoodByCategory(cat));
        }
         return foodList;
    }

    public List<Food> foodCategorySearchByRestaurant(String cat, String restaurant) {
        int searchIndex = 0;
        List<Food> foodList=new ArrayList<>();
        for (Restaurant res : restaurants) {
            if (res.getName().equalsIgnoreCase(restaurant)) {
                searchIndex =1;
                foodList = res.searchFoodByCategory(cat);

                break;
            }
        }
        if (searchIndex == 0) {
            System.out.println("No such restaurant with this name.");
        }
        return foodList;
    }

    public List<Food> foodSearchByPrice(double start, double end) {
        List<Food> foodList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            foodList.addAll(restaurant.searchFoodByPrice(start, end));
        }
        return foodList;
    }

    public List<Food> foodPriceSearchByRestaurant(double start, double end, String restaurant) {
        int searchIndex = 0;
        List<Food> foodList=new ArrayList<>();
        for (Restaurant res : restaurants) {
            if (res.getName().equalsIgnoreCase(restaurant)) {
                searchIndex =1;
                foodList = res.searchFoodByPrice(start, end);

                break;
            }
        }
        if (searchIndex == 0) {
            System.out.println("No such restaurant with this name.");
        }
        return foodList;
    }

    public List<Food> costliestFoodItems(String restaurant) {
        List<Food> foodList = new ArrayList<>();
        for (Restaurant restaurant1 : restaurants) {
            if (restaurant1.getName().equalsIgnoreCase(restaurant)) {
                foodList = restaurant1.costliestFoodItems();
                break;
            }
        }
        return foodList;
    }

    public List<String> listOfRestaurants() {
        List<String> list=new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            list.add(restaurant.getName() + ": " + restaurant.getFoodItems());
        }
        return list;
    }

    public void addFood(String restaurant, Food food) {
        int searchIndex = 0;
        for (Restaurant restaurant1 : restaurants) {
            if (restaurant1.getName().equalsIgnoreCase(restaurant)) {
                searchIndex = 1;
                restaurant1.addFood(food);
                break;
            }
        }
        if (searchIndex == 0) {
            System.out.println("No such restaurant with this name.");
        }
    }

    public boolean foodAvailable(int id,String foodName){
        for(Restaurant restaurant: restaurants){
            if(restaurant.getId() == id){
                return restaurant.isAvailable(foodName);
            }
        }
        return false;
    }
}
