import java.util.LinkedList;

public class ListTask {

    private static final LinkedList<String> elements = new LinkedList<>() {
        {
            add("First");
            add("Second");
            add("Third");
        }
    };
    private static int currentIndex = 0;
    private static int loopCount = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
//            System.out.println("Element: " + getNext());
            System.out.println("Element: " + getNext2());
        }

//        System.out.println("Number of complete traversals: " + getLoopCount());
        System.out.println("Number of complete traversals: " + getLoopCount2(9));
    }

    private static String getNext() {
        if (elements.isEmpty()) {
            return null;
        }

        String element = elements.get(currentIndex);

        currentIndex++;

        if (currentIndex >= elements.size()) {
            currentIndex = 0;
            loopCount++;
        }

        return element;
    }

    private static String getNext2() {
        if (elements.isEmpty()) {
            return null;
        }

        String element = elements.poll();
        elements.offerLast(element);

        return element;
    }

    public static int getLoopCount() {
        return loopCount;
    }

    public static int getLoopCount2(int maxI) {
        return maxI / elements.size();
    }
}
