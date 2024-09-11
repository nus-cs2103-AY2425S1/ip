package potong;

import java.io.InputStream;
import java.io.PrintStream;

import java.util.Scanner;

/**
 * Class to handle the user interface.
 */
public class Ui {

    private static final String LINE = "_________________________";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Initialise the user interface.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Initialise the user interface.
     *
     * @param in InputStream.
     * @param out PrintStream.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Get the next user input.
     *
     * @return User input.
     */
    public String getUserCommand() {
        this.showLine();
        return in.nextLine();
    }

    /**
     * Print the introduction when the program first runs.
     */
    public void showIntroduction() {
//        System.out.println("Hello! I'm potong.Potong");
//        System.out.println("What can I do for you?\n");
    }

    /**
     * Print a line to distinguish between each command.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Print the goodbye when the user terminates the program.
     */
    public void sayGoodbye() {
//        System.out.println("Bye. Hope to see you again soon!\n");
        this.showLine();
    }

    /**
     * Print any message.
     *
     * @param message Message to be printed.
     */
    public void print(String message) {
//        System.out.println(message);
    }

}
