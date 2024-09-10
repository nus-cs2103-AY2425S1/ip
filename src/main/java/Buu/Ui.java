package Buu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Ui (User Interface) class handles all interactions with the user in the GPT application.
 * This class is responsible for displaying messages, reading user input, and providing feedback based on user commands.
 */
public class Ui {
    private Scanner scanner;
    private List<String> outputBuffer; // Buffer to store output messages

    /**
     * Constructs a Ui object that uses a Scanner to read user input from the console.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.outputBuffer = new ArrayList<>();
    }

    /**
     * Displays a welcome message to the user when the application starts.
     *
     * @param chatbotName The name of the chatbot to be displayed in the welcome message.
     */
    public void showWelcomeMessage(String chatbotName) {
        outputBuffer.add("Hello! I'm " + chatbotName + "\nWhat can I do for you?");
    }
    /**
     * Displays a goodbye message to the user when the application exits.
     */
    public void showGoodbyeMessage() {
        outputBuffer.add("Bye. Hope to see you again soon!");
    }
    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        outputBuffer.add(message);
    }

    /**
     * Displays a message to the user when a task is added to the list.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks in the list after adding the new task.
     */
    public void showTaskAdded(Task task, int taskCount) {
        outputBuffer.add("Got it. I've added this task:\n  "
                + task + "\nNow you have " + taskCount + " tasks in the list.");
    }
    /**
     * Displays a confirmation message indicating that the task has been marked as done.
     *
     * @param task The task that has been marked as completed.
     */
    public void showTaskMarkedDone(Task task) {
        outputBuffer.add("Nice! I've marked this task as done:");
        outputBuffer.add("  " + task);
    }
    /**
     * Displays a message when a task is unmarked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        outputBuffer.add("OK, I've marked this task as not done yet:");
        outputBuffer.add("  " + task);
    }

    /**
     * Displays a message to the user when a task is removed from the list.
     *
     * @param task      The task that was removed.
     * @param taskCount The total number of tasks remaining in the list after removing the task.
     */
    public void showTaskRemoved(Task task, int taskCount) {
        outputBuffer.add("Noted. I've removed this task:\n  " + task);
        outputBuffer.add("Now you have " + taskCount + " tasks left in the list.");
    }

    /**
     * Displays the current list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            outputBuffer.add("No tasks to show.");
        } else {
            StringBuilder taskListMessage = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                taskListMessage.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
            }
            outputBuffer.add(taskListMessage.toString());
        }
    }
    /**
     * Displays a message to confirm that the priority has been set for the specified task.
     * This method adds a message to the output buffer, indicating that the priority of the
     * task has been successfully updated, and then shows the task's details using its
     * {@code toString()} method.
     *
     * @param task The task whose priority was set. The task's details will be displayed
     *             along with the newly set priority.
     */
    public void showTaskPrioritySet(Task task) {
        outputBuffer.add("OK, I've set the priority for this task:");
        outputBuffer.add("  " + task); // Calls the task's toString() method
    }

    /**
     * Displays the list of tasks that match the search keyword.
     *
     * @param tasks The list of tasks that match the search keyword.
     */
    public void showMatchingTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            outputBuffer.add("No matching tasks found.");
        } else {
            StringBuilder matchingTasksMessage = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                matchingTasksMessage.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
            }
            outputBuffer.add(matchingTasksMessage.toString());
        }
    }

    /**
     * Clears the output buffer.
     */
    public void clearOutputBuffer() {
        outputBuffer.clear();
    }

    /**
     * Retrieves all the messages stored in the output buffer.
     *
     * @return The list of buffered messages.
     */
    public List<String> getOutputBuffer() {
        List<String> output = new ArrayList<>(outputBuffer);
        outputBuffer.clear();
        return output;
    }

    public void addToOutputBuffer(String message) {
        outputBuffer.add(message);
    }
}
