package alfred.ui;

import java.io.IOException;
import java.util.List;

import alfred.exception.AlfredException;
import alfred.task.Task;

/**
 * Handles the generation of response messages in the Alfred application.
 */
public class AlfredResponse {

    /**
     * Generates a greeting message when the application starts.
     *
     * @return The greeting message.
     */
    public static String greet() {
        return "Good day Sir. I am Alfred, your English butler.\nHow can I help you today?";
    }

    /**
     * Generates an error message specific to Alfred-related exceptions.
     *
     * @param e The AlfredException that was thrown.
     * @return The error message.
     */
    public static String showAlfredError(AlfredException e) {
        return e.getMessage();
    }

    /**
     * Generates a message indicating that a task has been added to the task list.
     *
     * @param task The task that was added.
     * @param totalTasks The current number of tasks in the list.
     * @return The message indicating the task addition.
     */
    public static String showAddedTaskMessage(Task task, int totalTasks) {
        return "Got it Sir. The following task has been added to your list:\n    "
                + task + "\nYou now have " + totalTasks + " tasks awaiting your attention.";
    }

    /**
     * Generates a farewell message when the user exits the application.
     *
     * @return The farewell message.
     */
    public static String farewell() {
        return "Farewell Sir. Should you need anything, you know where to find me.";
    }

    /**
     * Generates a message indicating that the user entered an invalid command.
     *
     * @return The invalid command message.
     */
    public static String showInvalidCommand() {
        return "Terribly sorry Sir, I have no idea what you are saying.";
    }

    /**
     * Generates the current list of tasks, with each task numbered sequentially.
     *
     * @param tasks The list of tasks to display.
     * @return The string representation of the task list.
     */
    public static String getTaskList(List<Task> tasks) {
        return formatTasks(tasks);
    }

    /**
     * Generates message when task list is empty
     *
     * @return String message saying that there are no tasks
     */
    public static String getNoTasksMessage() {
        return "Outstanding Sir, there are no tasks in your list.";
    }

    /**
     * Generates a message listing all tasks that match the search keyword.
     *
     * @param foundTasks The list of tasks that match the search keyword.
     * @return The message listing found tasks.
     */
    public static String getFoundTasks(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            return "No matching tasks found Sir.";
        }
        String messageHeader = "Of course Sir, here are the matching tasks in your list:\n";
        return messageHeader + formatTasks(foundTasks);
    }

    private static String formatTasks(List<Task> foundTasks) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < foundTasks.size(); i++) {
            int taskNumber = i + 1;
            Task task = foundTasks.get(i);

            response.append(taskNumber)
                    .append(". ")
                    .append(task)
                    .append("\n");
        }
        return response.toString().trim();
    }

    /**
     * Generates a message indicating that a task has been marked as completed.
     *
     * @param task The task that was marked as done.
     * @return The message indicating task completion.
     */
    public static String showTaskMarked(Task task) {
        return "Indeed, Sir, the task has been duly completed:\n    " + task;
    }

    /**
     * Generates a message indicating that a task has been unmarked (marked as incomplete).
     *
     * @param task The task that was marked as not done.
     * @return The message indicating the task remains incomplete.
     */
    public static String showTaskUnmarked(Task task) {
        return "Very well Sir, the task remains outstanding:\n    " + task
                + "\nA reminder that even small tasks deserve attention.";
    }

    /**
     * Generates a message indicating that a task has been deleted from the task list.
     *
     * @param task The task that was deleted.
     * @param remainingTasks The number of tasks remaining after the deletion.
     * @return The message indicating task deletion.
     */
    public static String showTaskDeleted(Task task, int remainingTasks) {
        return "Of course Sir, the task has been successfully removed.\n    " + task
                + "\nYour list now contains " + remainingTasks + " tasks.";
    }

    /**
     * Generates a message indicating that the command format entered by the user was invalid.
     *
     * @return The invalid command format message.
     */
    public static String showInvalidCommandFormat() {
        return "I regret to inform you, Sir, that the command you entered is not recognized.\n"
                + "Please check the instructions, and I shall be ready to assist you further.";
    }

    /**
     * Generates an error message when the user attempts to access a task number that does not exist.
     *
     * @param taskCount The total number of tasks currently in the list.
     * @return The error message for invalid task number.
     */
    public static String showInvalidTaskNumber(int taskCount) {
        return "Apologies, Sir, but that task number is not valid.\n"
                + "You currently have only " + taskCount + " items in the list.\n"
                + "Might I suggest reviewing the list before proceeding?";
    }

    /**
     * Generates an error message when there is an issue loading tasks from storage.
     *
     * @param e The IOException that was thrown during loading.
     * @return The loading error message.
     */
    public static String showLoadingError(IOException e) {
        return "Error loading tasks: " + e.getMessage();
    }

    /**
     * Generates an error message when there is an issue saving tasks to storage.
     *
     * @param e The IOException that was thrown during saving.
     * @return The saving error message.
     */
    public static String showSavingError(IOException e) {
        return "Error saving tasks: " + e.getMessage();
    }

    /**
     * Generates an error message when a corrupted save file is detected.
     * Suggests to the user that they need to recreate their task list.
     *
     * @param e The AlfredException related to the corrupted save file.
     * @return The corrupted save file error message.
     */
    public static String showCorruptedSaveError(AlfredException e) {
        return "Terribly sorry Sir, I have misplaced your list of tasks.\n"
                + "To be more exact, the situation is as follows - " + e.getMessage() + "\n"
                + "Please create your list again.";
    }

    /**
     * Generates an error message when an unexpected exception occurs.
     *
     * @param e The Exception that was thrown unexpectedly.
     * @return The unexpected error message.
     */
    public static String showUnexpectedError(Exception e) {
        return "Unexpected error: " + e.getMessage();
    }
}
