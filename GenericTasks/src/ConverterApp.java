import java.util.List;

public class ConverterApp {

    public static void main(String[] args) {
        Converter converter = new Converter();
        String[] stringArray = {"Hello", "World"};
        List<String> items = converter.toList(stringArray);
        for (String item : items) {
            System.out.println(item);
        }
    }

}
