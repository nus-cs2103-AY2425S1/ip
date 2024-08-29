package patrick.ui;

import patrick.tasklist.Task;
import patrick.storage.Storage;

import java.io.*;
import java.util.Scanner;

/**
 * The {@code Ui} class handles all user interactions.
 * It manages input and output operations and displays messages to the user.
 */
public class Ui {
    // Constants for displaying UI elements and messages
    static String HORIZONTAL_LINE = "____________________________________________________________\n";
    static String GREETING_MSG = HORIZONTAL_LINE + "Hello! I'm Patrick, Spongebob's bestie\nHow can I help you?\n" + HORIZONTAL_LINE;
    static String EXIT_MSG = HORIZONTAL_LINE + "Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE;
    static String TASK_MSG = "Got it. I've added this task:\n";
    static String NUM_TASK_MSG_1 = "Now you have ";
    static String NUM_TASK_MSG_2 = " tasks in the list.\n";

    private final Scanner in;  // Scanner for user input
    private final PrintStream out;  // PrintStream for output
    private static ByteArrayOutputStream capturedOutput;  // For capturing output (optional)
    private static PrintStream originalOut;  // Original output stream

    /**
     * Default constructor initializing {@code Ui} with standard input and output.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructor initializing {@code Ui} with custom input and output streams.
     *
     * @param in  Input stream for user commands.
     * @param out Output stream for displaying messages.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Displays the welcome message and the current list of tasks.
     */
    public void welcomeMessage() {
        System.out.println(GREETING_MSG);
        printFileContents();
    }

    /**
     * Returns the current output stream.
     *
     * @return the current {@code PrintStream}.
     */
    public PrintStream getOut() {
        return this.out;
    }

    /**
     * Prompts the user for a command and returns their input.
     *
     * @return the user's command as a {@code String}.
     */
    public String getUserCommand() {
        out.print(HORIZONTAL_LINE + "What do you want to do: \n");
        return in.nextLine();
    }

    /**
     * Displays the goodbye message when the user exits the program.
     */
    public void showGoodbyeMsg() {
        out.print(HORIZONTAL_LINE + EXIT_MSG + HORIZONTAL_LINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param error the error message to display.
     */
    public static void showErrorMsg(String error) {
        System.out.println(HORIZONTAL_LINE + error + HORIZONTAL_LINE);
    }

    /**
     * Displays a message to the user after a task is added.
     *
     * @param msg the task details to display.
     */
    public static void showUserMsg(String msg) {
        System.out.println(HORIZONTAL_LINE + Ui.TASK_MSG + msg + "\n" + Ui.NUM_TASK_MSG_1
                + Storage.getList().size() + Ui.NUM_TASK_MSG_2 + HORIZONTAL_LINE);
    }

    /**
     * Displays a message to the user after a task is deleted.
     *
     * @param num the index of the deleted task.
     */
    public static void showDeleteItemMsg(int num) {
        System.out.println(HORIZONTAL_LINE + "Noted. I've removed this task:\n"
                + Storage.getList().get(num - 1).toString() + "\n" + Ui.NUM_TASK_MSG_1 + (Storage.getList().size() - 1) + Ui.NUM_TASK_MSG_2);
    }

    /**
     * Displays a message to the user after a task is marked or unmarked.
     *
     * @param msg  the action performed (mark/unmark).
     * @param task the task details to display.
     */
    public static void showMarkUnmarkMsg(String msg, String task) {
        System.out.println(HORIZONTAL_LINE + msg + task + HORIZONTAL_LINE);
    }

    /**
     * Prints the contents of the task list to the user.
     */
    public static void printFileContents() {
        System.out.println(Ui.HORIZONTAL_LINE + "Here are the tasks in your list:");
        for (int i = 1; i <= Storage.getList().size(); i++) {
            Task curr = (Task) Storage.getList().get(i - 1);
            System.out.println(i + ". " + curr.toString());
        }
        System.out.println(Ui.HORIZONTAL_LINE);
    }

    /**
     * Displays the supported date formats to the user.
     */
    public static void formats() {
        System.out.println(HORIZONTAL_LINE + "Here are the different formats available:");
        System.out.println("yyyy-MM-dd HHmm");
        System.out.println("dd-MM-yyyy HHmm");
        System.out.println("d-MM-yyyy HHmm");
        System.out.println("MM-dd-yyyy HHmm");
        System.out.println("yyyy/MM/dd HHmm");
        System.out.println("dd/MM/yyyy HHmm");
        System.out.println("d/MM/yyyy HHmm");
        System.out.println("MM/dd/yyyy HHmm");
        System.out.println("MMM dd yyyy HHmm");
        System.out.println("MMM d yyyy HHmm");
    }

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }
    public static void showMsg(String input) {
        System.out.print(input);
    }
}
