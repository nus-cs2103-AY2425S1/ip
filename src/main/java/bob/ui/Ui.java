package bob.ui;

import java.util.Scanner;

import bob.commands.Remind;
import bob.data.TaskList;
import bob.storage.Storage;

/**
 * Class to represent interactions with the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructor for user interface.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows welcome message to the user.
     */
    public static String showWelcome() {
        String logo = "Bob";
        return "Hello! I'm " + logo + "\n" + "What can I do for you?";
    }

    /**
     * Shows reminders of upcoming tasks to the user.
     */
    public static String showReminders(TaskList taskList) {
        Remind remind = new Remind();
        return remind.getReminders(taskList);
    }
}
