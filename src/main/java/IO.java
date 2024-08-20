import java.util.Arrays;
import java.util.List;

public class IO {
  public static void print(String msg) {
    List<String> msgList = Arrays.asList(msg.split("\n"));

    System.out.println("    ____________________________________________________________");
    for (String s : msgList) {
      System.out.println("      " + s);
    }
    System.out.println("    ____________________________________________________________");
  }
}
