package ipman;

/**
 * Handles user interface interactions.
 * This class provides methods to display messages and manage output buffers.
 * It includes functionality to print banners, separators, and buffered messages.
 *
 * @author miloaisdino
 */
public class Ui {
    public static final String SEPARATOR = "____________________________________________________________";
    private String buffer = "";

    /**
     * Constructs a Ui instance.
     */
    public Ui() {
    }

    /**
     * Prints the welcome banner
     */
    public void showBanner() {
        System.out.println(SEPARATOR);
        System.out.println("Hello from Ip Man!\nWhat can I do for you?");
        System.out.println(SEPARATOR);
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
     * Outputs the buffer content to the screen and clears the buffer.
     * If the buffer is not empty, it prints the buffer content followed by a separator line.
     */
    public void outputBuffer() {
        if (!buffer.isEmpty()) {
            System.out.println(buffer);
            printSeparator();
            flushBuffer();
        }
    }

    /**
     * Outputs the buffer content to the screen and clears the buffer.
     * If the buffer is not empty, it prints the buffer content followed by a separator line.
     */
    public void flushBuffer() {
        buffer = "";
    }
}
