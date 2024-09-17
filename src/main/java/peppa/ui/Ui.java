package peppa.ui;

import java.util.Scanner;

import peppa.commands.Remind;
import peppa.data.TaskList;

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
        String logo = "Peppa";
        return "Hello! My name is Peppa. " + logo + "\n" + "What can I do for you?";
    }

    /**
     * Shows reminders of upcoming tasks to the user.
     */
    public static String showReminders(TaskList taskList) {
        Remind remind = new Remind();
        return remind.getReminders(taskList);
    }
}
