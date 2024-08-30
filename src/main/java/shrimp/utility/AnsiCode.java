package shrimp.utility;

/**
 * A utility class that provides ANSI escape codes for terminal text formatting.
 * These codes can be used to apply color and text styles to console output.
 */
public class AnsiCode {
    /** ANSI escape code to reset text formatting. */
    public static final String RESET = "\u001B[0m";

    /** ANSI escape code for italic text. */
    public static final String ITALIC = "\u001B[3m";

    /** ANSI escape code for red text color. */
    public static final String RED = "\u001B[31m";

    /** ANSI escape code for purple text color. */
    public static final String PURPLE = "\u001B[35m";

    /** ANSI escape code for cyan text color. */
    public static final String CYAN = "\u001B[36m";}
