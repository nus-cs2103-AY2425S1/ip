/**
 * A class that holds commonly used functions within the Chat Bot
 * @author Colin Hia Qingxuan
 */
public class CommonFunctions {
    /**
     * Public method that prints a string between two horizontal lines.
     * @param s The string to be wrapped in horizontal lines.
     */
    public static void WrappedLinePrint(String s) {
        String HORIZONTAL_LINE = "_______________________________________";
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\n" + s + "\n");
        System.out.println(HORIZONTAL_LINE);
    }

}
