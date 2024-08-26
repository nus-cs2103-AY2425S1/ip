package ipman;

/**
 * Ui class
 * @author miloaisdino
 */
public class Ui {
    public static final String SEPARATOR = "____________________________________________________________";
    private String buffer = "";
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

    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public void addToBuffer(String str) {
        buffer = buffer + "\r\n" + str;
    }

    /**
     * Sends the buffer to the screen and clears it after
     */
    public void outputBuffer() {
        if (!buffer.isEmpty()) {
            System.out.println(buffer);
            printSeparator();
            flushBuffer();
        }
    }

    public void flushBuffer() {
        buffer = "";
    }
}
