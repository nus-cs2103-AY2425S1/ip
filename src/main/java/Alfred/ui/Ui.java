package alfred.ui;

import alfred.task.Task;
import alfred.exception.AlfredException;

import java.io.IOException;
import java.util.List;

/**
 * Handles all user interface interactions in the Alfred application.
 */
public class Ui {

    /**
     * Displays a greeting message when the application starts.
     */
    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Good day Sir. I am Alfred, your English butler.");
        System.out.println("How can I help you today?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message specific to Alfred-related exceptions.
     *
     * @param e The <code>AlfredException</code> that was thrown.
     */
    public static void showAlfredError(AlfredException e) {
        System.out.println("____________________________________________________________");
        System.out.println(e.getMessage());
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task The task that was added.
     * @param totalTasks The current number of tasks in the list.
     */
    public static void showAddedTaskMessage(Task task, int totalTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it Sir. The following task has been added to your list:");
        System.out.println("    " + task);
        System.out.println("You now have " + totalTasks + " tasks awaiting your attention.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public static void farewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Farewell Sir. Should you need anything, you know where to find me.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that the user entered an invalid command.
     */
    public static void showInvalidCommand() {
        System.out.println("____________________________________________________________");
        System.out.println("Terribly sorry Sir, I have no idea what you are saying.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the current list of tasks, with each task numbered sequentially.
     *
     * @param lis The list of tasks to display.
     */
    public static void showList(List<Task> lis) {
        System.out.println("____________________________________________________________");
        int counter = 1;
        for (Task task : lis) {
            System.out.println(counter + ". " + task);
            counter++;
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message listing all tasks that match the search keyword.
     *
     * @param foundTasks The list of tasks that match the search keyword.
     */
    public static void showFoundTasks(List<Task> foundTasks) {
        System.out.println("____________________________________________________________");
        if (foundTasks.isEmpty()) {
            System.out.println("No matching tasks found Sir.");
        } else {
            System.out.println("Of course Sir, here are the matching tasks in your list:");
            int counter = 1;
            for (Task task : foundTasks) {
                System.out.println(counter + ". " + task);
                counter++;
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
    * Displays a message indicating that a task has been marked as completed.
    *
    * @param task The task that was marked as done.
    */
    public static void showTaskMarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Indeed, Sir, the task has been duly completed:");
        System.out.println("    " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been unmarked (marked as incomplete).
     *
     * @param task The task that was marked as not done.
     */
    public static void showTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Very well Sir, the task remains outstanding:");
        System.out.println("    " + task);
        System.out.println("A reminder that even small tasks deserve attention.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been deleted from the task list.
     *
     * @param task The task that was deleted.
     * @param remainingTasks The number of tasks remaining after the deletion.
     */
    public static void showTaskDeleted(Task task, int remainingTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Of course Sir, the task has been successfully removed.");
        System.out.println("    " + task);
        System.out.println("Your list now contains " + remainingTasks + " tasks. Efficiency is its own rewards.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message indicating that the command format entered by the user was invalid.
     */
    public static void showInvalidCommandFormat() {
        System.out.println("____________________________________________________________");
        System.out.println("I regret to inform you, Sir, that the command you entered is not recognized.");
        System.out.println("Please check the instructions, and I shall be ready to assist you further.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message when the user attempts to access a task number that does not exist.
     *
     * @param taskCount The total number of tasks currently in the list.
     */
    public static void showInvalidTaskNumber(int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Apologies, Sir, but that task number is not valid.");
        System.out.println("You currently have only " + taskCount + " items in the list.");
        System.out.println("Might I suggest reviewing the list before proceeding?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message when there is an issue loading tasks from storage.
     *
     * @param e The <code>IOException</code> that was thrown during loading.
     */
    public static void showLoadingError(IOException e) {
        System.out.println("Error loading tasks: " + e.getMessage());
    }

    /**
     * Displays an error message when there is an issue saving tasks to storage.
     *
     * @param e The <code>IOException</code> that was thrown during saving.
     */
    public static void showSavingError(IOException e) {
        System.out.println("Error saving tasks: " + e.getMessage());
    }

    /**
     * Displays an error message when a corrupted save file is detected.
     * Suggests to the user that they need to recreate their task list.
     *
     * @param e The <code>AlfredException</code> related to the corrupted save file.
     */
    public static void showCorruptedSaveError(AlfredException e) {
        System.out.println("Terribly sorry sir, I have misplaced your list of tasks.");
        System.out.println("To be more exact, the situation is as follows - ");
        System.out.println(e.getMessage());
        System.out.println("Please create your list again.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message when there is an issue deleting tasks from storage.
     *
     * @param e The <code>IOException</code> that was thrown during deletion.
     */
    public static void showDeletionError(IOException e) {
        System.out.println("Error deleting tasks: " + e.getMessage());
    }

    /**
     * Displays an error message when an unexpected exception occurs.
     *
     * @param e The <code>Exception</code> that was thrown unexpectedly.
     */
    public static void showUnexpectedError(Exception e) {
        System.out.println("Unexpected error: " + e.getMessage());
    }
}
