package util;

/**
 * Class storing constants and utility methods.
 */
public class Utility {
    public static final String LINE = "-------------";
    /** Indent set to 0 spaces for the GUI */
    public static final String INDENT = "";
    public static final String INDENTED_LINE = Utility.INDENT + Utility.LINE;
    public static final String NEW_LINE = System.lineSeparator();

    /**
     * Method to wrap the msg between two horizontal lines.
     *
     * @param msg The msg to be wrapped.
     */
    public static void prettyPrint(String msg) {
        System.out.println(Utility.INDENTED_LINE);
        System.out.println(msg);
        System.out.println(Utility.INDENTED_LINE);
    }
}
