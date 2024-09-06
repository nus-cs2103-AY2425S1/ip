package taskalyn;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Manages the user interactions.
 */
public class Ui {

    /**
     * Prints the given input within two horizontal lines (Unused for GUI).
     *
     * @param input Any message from the bot.
     */
    public void printLines(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + input);
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Returns the starting welcome message from Taskalyn.
     */
    public String showWelcome() {
        return "Hey! I'm Taskalyn, your personal Task Manager :)\n" +
                "    What can I do for you?";
    }

    public String showAddTaskMessage(Task task, TaskManager taskManager) {
        int numberofTasks = taskManager.getTaskSize();
        return "Got it, I've added this task to your list!\n" +
                "      " + task.toString() + "\n" + "    Wah bro... " +
                numberofTasks + (numberofTasks > 1 ? " tasks already!" : " task already!");
    }

    public String showDeleteTaskMessage(Task task, TaskManager taskManager) {
        int numberofTasks = taskManager.getTaskSize();
        return "Awesome bro! One task gone :D\n" +
                "      " + task.toString() + "\n" + "    Wah bro... " +
                numberofTasks + (numberofTasks > 1 ? " tasks already!" : " task already!");
    }

    public String showMarkTaskAsCompleteMessage(Task task) {
        return "Nice, I've marked this task as complete:\n" +
                "       " + task.toString();
    }

    public String showMarkTaskAsIncompleteMessage(Task task) {
        return "Ok, I've marked this task as incomplete:\n" +
                "       " + task.toString();
    }

    public String showSearchTasksByKeywordMessage(String message) {
        return message;
    }

    public String showByeMessage() {
        return "Bye! Hope to see you again soon!";
    }
}
