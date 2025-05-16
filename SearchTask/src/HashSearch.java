import java.util.*;

class HashSearch {

    private long neededTime;
    private long sortTime;

    List<Recipe> hashSearch(List<Recipe> recipes, List<String> ingredientNames) {
        long startNeededTime = System.nanoTime();

        List<Recipe> result = new ArrayList<>();

        long startTime = System.nanoTime();

        HashMap<Integer, HashMap<String, Recipe>> amountMap = new HashMap<>();
        for (Recipe recipe : recipes) {
            Arrays.sort(recipe.neededIngredients(), Comparator.comparing(Ingredient::name));
            int amountOfIngredients = recipe.neededIngredients().length;
            if (!amountMap.containsKey(amountOfIngredients)) {
                amountMap.put(amountOfIngredients, new HashMap<>());
            }
            HashMap<String, Recipe> recipeMap = amountMap.get(amountOfIngredients);
            String key = Arrays.toString(recipe.neededIngredients());
            if (!recipeMap.containsKey(key)) {
                recipeMap.put(key, recipe);
            }
        }

        Collections.sort(ingredientNames);

        long endTime = System.nanoTime();
        sortTime = endTime - startTime;

        Recipe item = amountMap.get(ingredientNames.size()).get(Arrays.toString(ingredientNames.toArray()));
        result.add(item);
        
        long endNeededTime = System.nanoTime();
        neededTime = endNeededTime - startNeededTime;

        return result;
    }

    long getNeededTime() {
        return neededTime;
    }

    long getSortTime() {
        return sortTime;
    }

}
