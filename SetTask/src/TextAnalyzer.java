import java.util.HashSet;
import java.util.TreeSet;

class TextAnalyzer {

    private final String[] words;

    TextAnalyzer(String text) {
        words = text.split(" ");
    }

    TreeSet<String> getCommonWords(String otherText) {
        TreeSet<String> commonWords = new TreeSet<>();
        for (String word : words) {
            if (otherText.contains(word)) {
                commonWords.add(word);
            }
        }
        return commonWords;
    }

    HashSet<String> getUniqueWords(String otherText) {
        HashSet<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            if (!otherText.contains(word)) {
                uniqueWords.add(word);
            }
        }
        return uniqueWords;
    }

}
