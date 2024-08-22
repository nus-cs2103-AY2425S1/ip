/** Defines a set of utility functions that can be used throughout this package */
public class Utilities {

    public static final String line = "------------------------------";
    public static void printLine() {
        System.out.println(line);
    }

    public static void printLine(int space) {
        StringBuilder str = new StringBuilder();
        str.append("\n".repeat(Math.max(0, space)));
        str.append(line);
        str.append("\n".repeat(Math.max(0, space)));
        System.out.println(str.toString());
    }

}
