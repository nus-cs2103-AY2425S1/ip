package util;

public class Utility {
  public static final String LINE = "-------------";
  public static final String INDENT = "    ";
  public static final String INDENTED_LINE = Utility.INDENT + Utility.LINE;

  public static void prettyPrint(String msg) {
    System.out.println(Utility.INDENTED_LINE);
    System.out.println(msg);
    System.out.println(Utility.INDENTED_LINE);
  }
}
