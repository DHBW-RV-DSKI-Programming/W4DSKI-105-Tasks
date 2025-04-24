public class TextAnalyzerApp {
    
    public static void main(String[] args) {
        String text1 = "life is like a box of chocolates you never know what you are going to get until you take a bite and discover the sweet surprise inside";
        String text2 = "every man dies but not every man really lives so take the chance and make your life extraordinary while you have time to make a difference";

        TextAnalyzer analyzer = new TextAnalyzer(text1);
        System.out.println("Common words: " + analyzer.getCommonWords(text2));
        System.out.println("Unique words in first quote: " + analyzer.getUniqueWords(text2));
    }
    
}
