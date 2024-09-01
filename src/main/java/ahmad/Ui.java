package ahmad;

import java.util.Arrays;
import java.util.List;

/**
 * Class responsible for IO operations.
 */
public class Ui {
  /**
   * Prints message to user.
   *
   * @param msg Message to be printed.
   */
  public static void print(String msg) {
    List<String> msgList = Arrays.asList(msg.split("\n"));

    System.out.println("    ____________________________________________________________");
    for (String s : msgList) {
      System.out.println("      " + s);
    }
    System.out.println("    ____________________________________________________________");
  }
}
