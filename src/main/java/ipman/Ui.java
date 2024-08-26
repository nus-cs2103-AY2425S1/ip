package ipman;

public class Ui {
    public static final String SEPARATOR = "____________________________________________________________";
    public String buffer = "";
    public Ui() {
    }

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
