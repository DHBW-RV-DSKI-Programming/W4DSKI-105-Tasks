import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Product(String name, String description, double price, String category) {}

public class ProductCatalogBuilder {

    private static final List<Product> products = new ArrayList<>();
    private final StringBuilder catalogBuilder = new StringBuilder(1024);

    void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    private String toHtml(Product product) {
        return """
            <div class="product">
                <h2>%s</h2>
                <p>%s</p>
                <span class="price">%.2f €</span>
                <span class="category">%s</span>
            </div>
            """.formatted(
                escapeHtml(product.name()),
                escapeHtml(product.description()),
                product.price(),
                escapeHtml(product.category())
            );
    }

    public String applyPriceFilter(double minPrice, double maxPrice) {
        List<Product> filtered = products.stream()
                .filter(p -> p.price() >= minPrice && p.price() <= maxPrice)
                .toList();
        
        return generateCatalogByCategorySection(filtered);
    }
    
    private String generateCatalogByCategorySection(List<Product> productList) {
        Map<String, List<Product>> categoryMap = productList.stream()
                .collect(Collectors.groupingBy(Product::category));
                
        catalogBuilder.setLength(0); // Reset buffer
        
        for (Map.Entry<String, List<Product>> entry : categoryMap.entrySet()) {
            catalogBuilder.append("<section class=\"category-section\">\n");
            catalogBuilder.append("<h1>").append(escapeHtml(entry.getKey())).append("</h1>\n");
            for (Product product : entry.getValue()) {
                catalogBuilder.append(toHtml(product)).append("\n");
            }
            catalogBuilder.append("</section>\n");
        }
        
        return catalogBuilder.toString();
    }

    private String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
    
    public static void main(String[] args) {
        ProductCatalogBuilder builder = new ProductCatalogBuilder();
        
        builder.addProduct(new Product("Laptop", "High-performance laptop with SSD", 899.99, "Electronics"));
        builder.addProduct(new Product("Smartphone", "Latest model with 128GB storage", 699.99, "Electronics"));
        builder.addProduct(new Product("Coffee Maker", "Programmable coffee machine", 79.99, "Kitchen Appliances"));
        builder.addProduct(new Product("Blender", "Multi-speed blender for smoothies", 49.99, "Kitchen Appliances"));
        builder.addProduct(new Product("Desk Chair", "Ergonomic office chair", 199.99, "Furniture"));
        
        System.out.println("FULL CATALOG:");
        System.out.println(builder.generateCatalogByCategorySection(products));
        
        System.out.println("\nFILTERED CATALOG (50-200 EUR):");
        System.out.println(builder.applyPriceFilter(50.0, 200.0));
    }
}