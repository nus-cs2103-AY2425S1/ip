package patrick.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import patrick.storage.Storage;
import patrick.tasklist.Task;

/**
 * The {@code Ui} class handles all user interactions.
 * It manages input and output operations and displays messages to the user.
 */
public class Ui {
    // Constants for displaying UI elements and messages
    public static final String THERE_IS_AN_ERROR = "There is an error: ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final String EXIT_MSG = HORIZONTAL_LINE + "Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE;
    private static final String TASK_MSG = "Got it. I've added this task:\n";
    private static final String NUM_TASK_MSG_1 = "Now you have ";
    private static final String NUM_TASK_MSG_2 = " tasks in the list.\n";


    private final Scanner in; // Scanner for user input
    private final PrintStream out; // PrintStream for output

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
     * Displays a message to the user after a task is added.
     *
     * @param msg the task details to display.
     */
    public static String showUserMsg(String msg) {
        return Ui.TASK_MSG + msg + "\n" + Ui.NUM_TASK_MSG_1 + Storage.getList().size() + Ui.NUM_TASK_MSG_2;
    }

    /**
     * Displays a message to the user after a task is deleted.
     *
     * @param num the index of the deleted task.
     */
    public static String showDeleteItemMsg(int num) {
        return "Noted. I've removed this task:\n"
                + Storage.getList().get(num - 1).toString() + "\n" + Ui.NUM_TASK_MSG_1 + (Storage.getList().size() - 1)
                + Ui.NUM_TASK_MSG_2;
    }

    /**
     * Prints the contents of the task list to the user.
     */
    public static String printFileContents() {
        String list = "Here are the tasks in your list:" + "\n";

        for (int i = 1; i <= Storage.getList().size(); i++) {
            Task curr = Storage.getList().get(i - 1);
            list += i + ". " + curr.toString() + "\n";
        }

        return list;
    }

    /**
     * Displays the supported date formats to the user.
     */
    public static String formats() {
        String response = "Here are the different formats available:\n" + "yyyy-MM-dd HHmm\n" + "dd-MM-yyyy HHmm\n"
                + "d-MM-yyyy HHmm\n" + "MM-dd-yyyy HHmm\n" + "yyyy/MM/dd HHmm\n" + "dd/MM/yyyy HHmm\n"
                + "d/MM/yyyy HHmm\n" + "MM/dd/yyyy HHmm\n" + "MMM dd yyyy HHmm\n" + "MMM d yyyy HHmm\n";
        return response;
    }

    public static String showErrorMsg(String message) {
        return message;
    }
}
