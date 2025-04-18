import java.util.ArrayList;
import java.util.List;

public class Converter {

    <T> List<T> toList(T[] array) {
        List<T> list = new ArrayList<>();
        for (T element : array) {
            list.add(element);
        }
        return list;
    }

}
