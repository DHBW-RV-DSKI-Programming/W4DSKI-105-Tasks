import java.util.Arrays;

record Recipe(Ingredient[] neededIngredients) {

    @Override
    public String toString() {
        return neededIngredients.length + " ingredients: " + Arrays.toString(neededIngredients);
    }

}
