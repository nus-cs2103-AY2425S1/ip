import java.util.Scanner;

/**
 * This class represents a reply from the chatbot.
 * It provides methods to print a reply, such as greeting.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String INDENT = "    ";

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printMessage(String message) {
        System.out.println(INDENT + HORIZONTAL_LINE);
        String[] lines = message.split("\n");
        for (String line : lines) {
            System.out.println(INDENT + " " + line);
        }
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    /**
     * Print greeting message
     */
    public void printGreeting() {
        System.out.println(INDENT+ HORIZONTAL_LINE);
        System.out.println(INDENT + " Hello! I'm Carine");
        System.out.println(INDENT + " What can I do for you?");
        System.out.println(INDENT + HORIZONTAL_LINE);
    }
    /**
     * Print goodbye message
     */
    public void printGoodbye() {
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(INDENT + " Bye. Hope to see you again soon!");
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

}
