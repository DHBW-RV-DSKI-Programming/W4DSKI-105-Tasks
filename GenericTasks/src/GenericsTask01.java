public class GenericsTask01 {

    public static void main(String[] args) {
        Pair<String, Double> pair = new Pair<>("Fight Club", 10.0);
        System.out.println("First: " + pair.getT() + "; Second: " + pair.getU());
        System.out.println("---------------------");

        Pair<Double, String> swappedPair = pair.swap();
        System.out.println("Swapped: " + swappedPair.getT() + "; Swapped: " + swappedPair.getU());
    }

}
