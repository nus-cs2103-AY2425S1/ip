package BrainRot;

import BrainRot.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * The BrainRot.Ui class handles all user interactions for the application.
 * It is responsible for displaying messages to the user, prompting for input,
 * and formatting the output. The BrainRot.Ui class abstracts away the details of input/output
 * operations, allowing the rest of the application to focus on core logic.
 */
public class Ui {

    private static final String LINE = "____________________________________________________________\n";

    protected static final String GREETING =
            "Hello! I'm fanumTaxRizzlerOhioSigmaLooksmaxxer\n"
                    + "What can I do for you?\n";

    protected static final String GOODBYE =
            "Bye. Hope to see you again soon!\n";

    protected static final String COMMANDERROR =
            "OOPS!!! I'm sorry, but I don't know what that means :-(\n";

    protected static final String ACTIVITYERROR =
            "OOPS!!! The description of an activity cannot be empty.\n";

    protected static final String LOADINGERROR =
            "OOPS!!! I'm sorry, your database is not loading properly :-(\n";

    /** A platform-independent line separator. */
    private static final String LS = System.lineSeparator();

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a BrainRot.Ui object using the default input and output streams.
     * The default streams are System.in for input and System.out for output.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a BrainRot.Ui object with specified input and output streams.
     *
     * @param in  The input stream to be used by the UI.
     * @param out The output stream to be used by the UI.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /** Shows the welcome message to the user. */
    public void showWelcomeMessage() {
        showToUser(LINE + GREETING + LINE);
    }

    /** Shows the exit message to the user. */
    public void showExit() {
        showToUser(GOODBYE + LINE);
    }

    /** Shows the loading error message if the database fails to load. */
    public void showLoadingError() {
        showToUser(LINE + LOADINGERROR + LINE);
    }

    /** Shows the activity error message if the task description is empty. */
    public void showActivityError() {
        showToUser(LINE + ACTIVITYERROR + LINE);
    }

    /** Shows the command error message if the user enters an unrecognized command. */
    public void showCommandError() {
        showToUser(LINE + COMMANDERROR + LINE);
    }

    /**
     * Shows a message when a task is marked as done.
     *
     * @param taskDetails The details of the task that was marked as done.
     */
    public void showMarkMsg(String taskDetails) {
        showToUser("Nice! I've marked this task as done:\n  " + taskDetails + LS);
    }

    /**
     * Shows a message when a task is unmarked as not done.
     *
     * @param taskDetails The details of the task that was unmarked.
     */
    public void showUnMarkMsg(String taskDetails) {
        showToUser("OK, I've marked this task as not done yet:\n  " + taskDetails + LS);
    }

    /**
     * Shows a message when a task is deleted.
     *
     * @param taskDetails The details of the task that was deleted.
     */
    public void showDeleteMsg(String taskDetails) {
        showToUser("Noted. I've removed this task:\n  " + taskDetails + LS);
    }

    /**
     * Shows a message when a task is added.
     *
     * @param taskDetails The details of the task that was added.
     */
    public void showAddTaskMsg(String taskDetails) {
        showToUser("Got it. I've added this task:\n  " + taskDetails + LS);
    }

    /**
     * Prompts the user for input and returns the entered command.
     * This method will ignore empty or whitespace-only inputs.
     *
     * @return The command entered by the user.
     */
    public String getUserCommand() {
        out.print("Enter command: ");
        String fullInputLine = in.nextLine();

        // Silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        showToUser("[Command entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is only whitespace or is empty.
     *
     * @param rawInputLine Full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * Shows one or more messages to the user.
     * This method handles formatting and ensures consistent output.
     *
     * @param messages The messages to be displayed to the user.
     */
    public void showToUser(String... messages) {
        for (String message : messages) {
            out.println(message.replace("\n", LS));
        }
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The BrainRot.TaskList containing tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder(LINE + "Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.getTask(i).toString()).append(LS);
        }
        sb.append(LINE);
        showToUser(sb.toString());
    }
}
