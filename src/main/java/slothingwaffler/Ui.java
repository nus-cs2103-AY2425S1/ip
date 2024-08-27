package slothingwaffler;

/**
 * Handles user interface interactions for the SlothingWaffler application.
 * <p>
 * This class provides methods for greeting, displaying task messages, and handling errors.
 * </p>
 */
public class Ui {

    /**
     * Prints a greeting message to the user.
     */
    public void greet() {
        System.out.println("""
                Hello! I'm the Slothing Waffler!
                Let's stop slothing and get cracking!""");
    }

    /**
     * Prints an exit message to the user.
     */
    public void exit() {
        System.out.println("See you next time! Remember to get a waffle!");
    }

    /**
     * Prints a message confirming that a task has been added.
     *
     * @param tasks the current list of tasks
     */
    public void addTaskMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints a message confirming that a task has been marked as done.
     *
     * @param tasks the current list of tasks
     * @param i the index of the task marked as done
     */
    public void markTaskMessage(TaskList tasks, int i) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(i).toString());
    }

    /**
     * Prints a message confirming that a task has been deleted.
     *
     * @param tasks the current list of tasks
     * @param taskNum the index of the task deleted
     */
    public void deleteTaskMessage(TaskList tasks, int taskNum) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(taskNum));
        int size = tasks.size() - 1;
        System.out.println("Now you have " + size + " tasks in the list.");
    }
}
