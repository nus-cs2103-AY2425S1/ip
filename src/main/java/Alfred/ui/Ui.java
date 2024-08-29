package alfred.ui;

import alfred.task.Task;
import alfred.exception.AlfredException;

import java.io.IOException;
import java.util.List;

public class Ui {

    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Good day Sir. I am Alfred, your English butler.");
        System.out.println("How can I help you today?");
        System.out.println("____________________________________________________________");
    }

    public static void showAlfredError(AlfredException e) {
        System.out.println("____________________________________________________________");
        System.out.println(e.getMessage());
        System.out.println("____________________________________________________________");
    }

    public static void showAddedTaskMessage(Task task, int totalTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it Sir. The following task has been added to your list:");
        System.out.println("    " + task);
        System.out.println("You now have " + totalTasks + " tasks awaiting your attention.");
        System.out.println("____________________________________________________________");
    }

    public static void farewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Farewell Sir. Should you need anything, you know where to find me.");
        System.out.println("____________________________________________________________");
    }

    public static void showInvalidCommand() {
        System.out.println("____________________________________________________________");
        System.out.println("Terribly sorry Sir, I have no idea what you are saying.");
        System.out.println("____________________________________________________________");
    }

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

    public static void showTaskMarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Indeed, Sir, the task has been duly completed:");
        System.out.println("    " + task);
        System.out.println("____________________________________________________________");
    }

    public static void showTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Very well Sir, the task remains outstanding:");
        System.out.println("    " + task);
        System.out.println("A reminder that even small tasks deserve attention.");
        System.out.println("____________________________________________________________");
    }

    public static void showTaskDeleted(Task task, int remainingTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Of course Sir, the task has been successfully removed.");
        System.out.println("    " + task);
        System.out.println("Your list now contains " + remainingTasks + " tasks. Efficiency is its own rewards.");
        System.out.println("____________________________________________________________");
    }

    public static void showInvalidCommandFormat() {
        System.out.println("____________________________________________________________");
        System.out.println("I regret to inform you, Sir, that the command you entered is not recognized.");
        System.out.println("Please check the instructions, and I shall be ready to assist you further.");
        System.out.println("____________________________________________________________");
    }

    public static void showInvalidTaskNumber(int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Apologies, Sir, but that task number is not valid.");
        System.out.println("You currently have only " + taskCount + " items in the list.");
        System.out.println("Might I suggest reviewing the list before proceeding?");
        System.out.println("____________________________________________________________");
    }

    public static void showLoadingError(IOException e) {
        System.out.println("Error loading tasks: " + e.getMessage());
    }

    public static void showSavingError(IOException e) {
        System.out.println("Error saving tasks: " + e.getMessage());
    }

    public static void showCorruptedSaveError(AlfredException e) {
        System.out.println("Terribly sorry sir, I have misplaced your list of tasks.");
        System.out.println("To be more exact, the situation is as follows - ");
        System.out.println(e.getMessage());
        System.out.println("Please create your list again.");
        System.out.println("____________________________________________________________");
    }

    public static void showDeletionError(IOException e) {
        System.out.println("Error deleting tasks: " + e.getMessage());
    }

    public static void showUnexpectedError(Exception e) {
        System.out.println("Unexpected error: " + e.getMessage());
    }
}
