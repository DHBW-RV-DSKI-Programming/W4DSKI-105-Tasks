public class MapTask04App {

    public static void main(String[] args) {
        MapTask04 t4 = new MapTask04();
        t4.addProduct("Electronics", "MacBook Pro", 50);
        t4.updateQuantity("Electronics", "MacBook Pro", 100);
        System.out.println(t4.getQuantity("Electronics", "MacBook Pro"));
        System.out.println(t4.getTotalProductsInCategory("Electronics"));
        System.out.println(t4.getAllProducts());
    }

}
