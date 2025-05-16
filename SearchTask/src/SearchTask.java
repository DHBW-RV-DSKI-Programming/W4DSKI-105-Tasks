import java.util.*;

public class SearchTask {

    private static final String[] POSSIBLE_INGREDIENTS = {
            "Tomato", "Chicken", "Basil", "Salt", "Pepper",
            "Garlic", "Olive Oil", "Onion", "Pasta", "Beef",
            "Cheese", "Potato", "Carrot", "Milk", "Flour",
            "Egg", "Butter", "Mushroom", "Spinach", "Rice",
            "Broccoli", "Lemon", "Honey", "Ginger", "Fish",
            "Pork", "Beans", "Corn", "Apple", "Sugar",
            "Yogurt", "Oats", "Avocado", "Coconut", "Chili",
            "Lettuce", "Turkey", "Pumpkin", "Cinnamon", "Vinegar"
    };

    public static void main(String[] args) {
        List<Recipe> recipes = generateRecipes();

        List<String> searchIngredients = Arrays.asList("Tomato", "Chicken", "Basil");
        System.out.println("Search ingredients: " + searchIngredients);

        System.out.println("\n======= LINEAR SEARCH =======");
        LinearSearch lSearch = new LinearSearch();
        List<Recipe> lFoundRecipes = lSearch.linearSearch(recipes, searchIngredients);
        printResults(lFoundRecipes);

        System.out.println("\n======= Hash SEARCH =======");

        HashSearch hSearch = new HashSearch();
        List<Recipe> bFoundRecipes = hSearch.hashSearch(recipes, searchIngredients);
        printResults(bFoundRecipes);
        long searchTime = hSearch.getNeededTime() - hSearch.getSortTime();

        System.out.println("\n======= PERFORMANCE COMPARISON =======");
        System.out.printf("Linear search time: %d ns\n", lSearch.getNeededTime());
        System.out.printf("Hash search time: %d ns (%d ns needed to prepare)\n", searchTime, hSearch.getSortTime());
        System.out.printf("Hash search is %.2f times faster than linear search", lSearch.getNeededTime() / (double) searchTime);
    }

    private static List<Recipe> generateRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        Random random = new Random();
        int randomIndex = random.nextInt(1000);

        for (int i = 0; i < 1000; i++) {
            int ingredientCount = 3 + random.nextInt(5); // Recipes with 3-7 ingredients
            Set<String> chosenNames = new HashSet<>();
            List<Ingredient> ingredients = new ArrayList<>();

            while (ingredients.size() < ingredientCount) {
                String ingredientName = POSSIBLE_INGREDIENTS[random.nextInt(POSSIBLE_INGREDIENTS.length)];
                if (chosenNames.add(ingredientName)) {
                    ingredients.add(new Ingredient(ingredientName));
                }
            }

            Recipe recipe = new Recipe(ingredients.toArray(new Ingredient[0]));
            recipes.add(recipe);

            if (i == randomIndex) {
                recipes.add(new Recipe(new Ingredient[]{new Ingredient("Tomato"), new Ingredient("Chicken"), new Ingredient("Basil")}));
            }
        }

        return recipes;
    }

    private static void printResults(List<Recipe> foundRecipes) {
        if (!foundRecipes.isEmpty()) {
            foundRecipes.stream().limit(5).forEach(System.out::println);
        } else {
            System.out.println("No recipes found");
        }
    }

}