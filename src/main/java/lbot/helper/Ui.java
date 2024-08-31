package lbot.helper;

import lbot.task.Task;

/**
 * This class handles all the printed message for user.
 */
public class Ui {
    /**
     * Prints out successfully added {@link Task}.
     *
     * @param task added.
     */
    public void printTaskAddedMessage(Task task) {
        System.out.println("Added: " + task);
    }

    /**
     * Prints out if {@link Task} has been successfully marked or unmarked.
     *
     * @param task that has been marked/unmarked.
     */
    public void printTaskMarkedMessage(Task task) {
        if (task.getStatus()) {
            System.out.println("Marked: " + task);
        } else {
            System.out.println("Unmarked: " + task);
        }
    }

    /**
     * Prints out {@link Task} has been successfully deleted.
     *
     * @param task that has been deleted.
     */
    public void printTaskDeletedMessage(Task task) {
        System.out.println("Deleted: " + task);
    }

    /**
     * Prints out {@link TaskList}.
     *
     * @param taskList contains all tasks added to LBot.
     */
    public void printTaskList(TaskList taskList) {
        System.out.println("Here are your tasks:");
        System.out.println(taskList);
    }

    /**
     * Prints initial greeting.
     */
    public void printGreeting() {
        System.out.println("Heyo, I'm LBot!");
        System.out.println("How can I help? :)");
    }

    /**
     * Prints farewell message.
     */
    public void printBye() {
        System.out.println("Bye-bi!!");
    }

    /**
     * Prints failure message if previous tasks could not be loaded.
     * Informs user that their tasks have been lost.
     */
    public void printDataLoadFailed() {
        System.out.println("Seems like there was an issue loading your tasks... Guess we gotta start from scratch :(");
    }

    /**
     * Formatted Exception message.
     *
     * @param output The exception message to be printed.
     */
    public void printException(String output) {
        System.out.println("Oopssie: " + output);
    }

    public void print(String output) {
        System.out.println(output);
    }
}
