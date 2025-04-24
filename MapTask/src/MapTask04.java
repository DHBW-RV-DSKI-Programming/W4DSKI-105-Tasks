import java.util.ArrayList;
import java.util.HashMap;

class MapTask04 {

    private final HashMap<String, HashMap<String, Integer>> products = new HashMap<>();
    
    MapTask04() {
        addProduct("Electronics", "Laptop", 10);
        addProduct("Electronics", "Smartphone", 25);
        addProduct("Electronics", "Tablet", 15);
        addProduct("Clothing", "T-Shirt", 100);
        addProduct("Clothing", "Jeans", 50);
        addProduct("Clothing", "Jacket", 30);
        addProduct("Books", "Novel", 75);
        addProduct("Books", "Textbook", 40);
        addProduct("Books", "Comics", 60);
        addProduct("Food", "Bread", 200);
        addProduct("Food", "Milk", 150);
        addProduct("Food", "Eggs", 300);
    }

    void addProduct(String category, String product, int quantity) {
        HashMap<String, Integer> productMap = products.getOrDefault(category, new HashMap<>());
        productMap.put(product, quantity);
        products.put(category, productMap);
    }

    void updateQuantity(String category, String product, int quantity) {
        HashMap<String, Integer> productMap = products.get(category);
        if (productMap != null) {
            int currentQuantity = productMap.getOrDefault(product, 0);
            productMap.put(product, currentQuantity + quantity);
        }
    }
    
    int getQuantity(String category, String product) {
        HashMap<String, Integer> productMap = products.get(category);
        return productMap != null ? productMap.getOrDefault(product, 0) : 0;
    }
    
    int getTotalProductsInCategory(String category) {
        HashMap<String, Integer> productMap = products.get(category);
        return productMap != null ? productMap.size() : 0;
    }

    
    ArrayList<ArrayList<String>> getAllProducts() {
        ArrayList<ArrayList<String>> productsList = new ArrayList<>();
        for (String category : products.keySet()) { // sorted by hash values
            HashMap<String, Integer> productMap = products.get(category);
            ArrayList<String> productList = new ArrayList<>(productMap.keySet());
            productsList.add(productList);
        }
        return productsList;
    }

}
