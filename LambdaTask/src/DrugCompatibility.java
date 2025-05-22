import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

record ActiveIngredient(String name) {
}

record DrugPatient(int age, double weight, List<String> allergies) {
}

record Drug(String name, ActiveIngredient activeIngredient, int minAge, int maxAge, double minWeight,
            double maxWeight) {
}

public class DrugCompatibility {
    public static void main(String[] args) {
        DrugPatient drugPatient = new DrugPatient(30, 70.0, List.of("Penicillin"));
        ActiveIngredient paracetamol = new ActiveIngredient("Paracetamol");
        ActiveIngredient penicillin = new ActiveIngredient("Penicillin");
        Drug drugA = new Drug("MediA", paracetamol, 12, 99, 40, 150);
        Drug drugB = new Drug("MediB", penicillin, 18, 65, 40, 90);

        Predicate<ActiveIngredient> isIngredientSuitable = ingredient ->
                !drugPatient.allergies().contains(ingredient.name());
        
        BiPredicate<DrugPatient, Drug> isDrugSuitable = (p, d) ->
                p.age() >= d.minAge() &&
                        p.age() <= d.maxAge() &&
                        p.weight() >= d.minWeight() &&
                        p.weight() <= d.maxWeight() &&
                        !p.allergies().contains(d.activeIngredient().name());
        
        Function<Drug, String> dosageRecommendation = d ->
                "Standard dosage for " + d.name() + ": " +
                        "between " + d.minAge() + "-" + d.maxAge() +
                        " years, weight " + d.minWeight() + "-" + d.maxWeight() + " kg.";
        
        System.out.println("Paracetamol suitable? " + isIngredientSuitable.test(paracetamol));
        System.out.println("Penicillin suitable? " + isIngredientSuitable.test(penicillin));
        
        System.out.println("DrugA suitable? " + isDrugSuitable.test(drugPatient, drugA));
        System.out.println("DrugB suitable? " + isDrugSuitable.test(drugPatient, drugB));

        System.out.println(dosageRecommendation.apply(drugA));
    }
}