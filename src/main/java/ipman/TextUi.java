package ipman;

/**
 * Handles user interface interactions.
 * This class provides methods to display messages and manage output buffers.
 * It includes functionality to print banners, separators, and buffered messages.
 *
 * @author miloaisdino
 */
public class TextUi {
    public static final String SEPARATOR = "____________________________________________________________";
    private String buffer = "";

    /**
     * Constructs a TextUi instance.
     */
    public TextUi() {
    }

    /**
     * Returns the welcome banner.
     */
    public static String getBanner() {
        return SEPARATOR + "\nHello from Ip Man!\nWhat can I do for you?\n" + SEPARATOR;
    }

    /**
     * Prints the welcome banner.
     */
    public void printBanner() {
        System.out.println(getBanner());
    }

    /**
     * Prints a separator line.
     * The separator line is defined by the SEPARATOR constant.
     */
    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Adds a string to the output buffer.
     * The string is appended to the current buffer content.
     *
     * @param str The string to add to the buffer.
     */
    public void addToBuffer(String str) {
        buffer = buffer + "\r\n" + str;
    }

    /**
     * Prints the buffer content to the screen and clears the buffer.
     * If the buffer is not empty, it prints the buffer content followed by a separator line.
     */
    public void printBuffer() {
        if (!buffer.isEmpty()) {
            System.out.println(buffer);
            printSeparator();
            flushBuffer();
        }
    }

    /**
     * Returns the buffer content and clears the buffer.
     * The buffer content is returned as a single string.
     *
     * @return The buffer content.
     */
    public String outputBuffer() {
        String output = buffer;
        flushBuffer();
        return output;
    }

    /**
     * Clears the buffer content.
     */
    public void flushBuffer() {
        buffer = "";
    }
}
