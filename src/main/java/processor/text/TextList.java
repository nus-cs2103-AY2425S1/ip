package processor.text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TextList {
  private static List<String> list = new ArrayList<String>();

  public static List<String> getStringList() {
    if (list.isEmpty()) {
      return List.of("No items!");
    }
    return List.of(IntStream.range(0, list.size()).mapToObj(i -> (i + 1) + ". " + list.get(i)).reduce("", (acc, cur) -> acc + '\n' + cur));
  }

  public static void addString(String newString) {
    list.add(newString);
  }
}
