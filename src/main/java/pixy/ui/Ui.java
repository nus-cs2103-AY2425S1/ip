/**
 * Handles the user interaction with the chatbot.
 */
package pixy.ui;

import pixy.tasks.Task;
import java.util.List;

public class Ui {

    /**
     * Shows the welcome message from chatbot at the beginning.
     */
    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Pixy.\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    /**
     * Displays the task after it has been added to the list in the desired format.
     *
     * @param task The task that has been added.
     * @param size The size of the task list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("____________________________________________________________\n");
        System.out.println("Got it. I've added this task:\n " + task);
        System.out.println("Now you have " + size + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    /**
     * Displays message when task list is empty.
     */
    public void showListEmpty() {
        System.out.println("____________________________________________________________\n"
                + "List is Empty! Add tasks to list.\n"
                + "\n____________________________________________________________\n");
    }

    /**
     * Prints the tasks sequentially from the task list.
     *
     * @param list The Task list.
     */
    public void showTasks(List<Task> list) {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Displays the task with appropriate message after it has been marked.
     * @param task The task to mark.
     */
    public void showTaskMarked(Task task) {
        System.out.println("____________________________________________________________\n");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString() +
                "\n____________________________________________________________\n");
    }

    /**
     * Displays the task with appropriate message after it has been unmarked.
     * @param task The task to unmark.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________\n");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString() +
                "\n____________________________________________________________\n");
    }

    /**
     * Displays the task with appropriate message after it has been deleted.
     *
     * @param task The task to delete.
     * @param size The size of the task list.
     */
    public void showTaskRemoved(Task task, int size) {
        System.out.println("____________________________________________________________\n");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    public void showMatchedTasks(List<Task> matchedTasks) {
        System.out.println("____________________________________________________________\n");
        if(matchedTasks.isEmpty()) {
            System.out.println("No tasks found with matching description.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchedTasks.size(); i++) {
                Task task = matchedTasks.get(i);
                System.out.println((i + 1) + ". " + task.toString());
            }
        }
        System.out.println("____________________________________________________________\n");
    }
    /**
     * Displays the goodbye message.
     */
    public void showGoodbyeMessage() {
        System.out.println("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    /**
     * Displays the appropriate message when error is encountered..
     */
    public void showError(String message) {
        System.out.println(message);
        System.out.println("____________________________________________________________\n");
    }
}
