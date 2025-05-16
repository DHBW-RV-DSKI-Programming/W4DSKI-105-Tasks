import java.util.ArrayList;
import java.util.List;

class LinearSearch {

    private long neededTime;

    List<Recipe> linearSearch(List<Recipe> recipes, List<String> ingredientNames) {
        long startTime = System.nanoTime();

        List<Recipe> result = new ArrayList<>();

        for (Recipe recipe : recipes) {
            boolean containsAll = true;

            // Check if the recipe contains all specified ingredients
            for (String targetIngredient : ingredientNames) {
                boolean found = false;
                for (Ingredient ingredient : recipe.neededIngredients()) {
                    if (ingredient.name().equals(targetIngredient)) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    containsAll = false;
                    break;
                }
            }

            if (containsAll && recipe.neededIngredients().length == ingredientNames.size()) {
                result.add(recipe);
                break; // Stop searching after the first match is found
            }
        }

        long endTime = System.nanoTime();
        neededTime = endTime - startTime;

        return result;
    }

    long getNeededTime() {
        return neededTime;
    }

}
