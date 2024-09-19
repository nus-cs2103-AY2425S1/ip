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
        outputBuffer.add("Goku? Is that really you? It's been so long!\n"
                + " Let’s take care of those tasks ");
    }
    /**
     * Displays a goodbye message to the user when the application exits.
     */
    public void showGoodbyeMessage() {
        outputBuffer.add("Bye bye, Goku! Hope to munch on more tasks with you soon! Buu will be waiting!");
    }
    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        outputBuffer.add("Goku, something went wrong! " + message + " Let's try that again!");
    }

    /**
     * Displays a message to the user when a task is added to the list.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks in the list after adding the new task.
     */
    public void showTaskAdded(Task task, int taskCount) {
        outputBuffer.add("I've added this task:\n "
                + task
                + "\nNow you've got " + taskCount + " tasks to take care of, Goku!");
    }
    /**
     * Displays a confirmation message indicating that the task has been marked as done.
     *
     * @param task The task that has been marked as completed.
     */
    public void showTaskMarkedDone(Task task) {
        outputBuffer.add("You've completed this task:\n  " + task);
        outputBuffer.add("Good job, Goku! Buu is impressed! Keep it going! \uD83D\uDE0B");
    }
    /**
     * Displays a message when a task is unmarked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        outputBuffer.add("Goku, looks like this task isn't done yet:\n  " + task);
        outputBuffer.add("Let's tackle it together soon! Buu's ready when you are! \uD83D\uDC4D");
    }

    /**
     * Displays a message to the user when a task is removed from the list.
     *
     * @param task      The task that was removed.
     * @param taskCount The total number of tasks remaining in the list after removing the task.
     */
    public void showTaskRemoved(Task task, int taskCount) {
        outputBuffer.add("Poof! This task is gone, Goku! \uD83D\uDCA8");
        outputBuffer.add("  " + task);
        outputBuffer.add("Now there are " + taskCount + " tasks left. Let's keep going!");
    }

    /**
     * Displays the current list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            outputBuffer.add("Hooray! No tasks left, Goku! Buu is happy! Time for a break! \uD83D\uDE0A");
        } else {
            StringBuilder taskListMessage = new StringBuilder("Here are your tasks, Goku! Let's take them down one by one:\n");
            for (int i = 0; i < tasks.size(); i++) {
                taskListMessage.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
            }
            taskListMessage.append("Buu knows you can do this! Keep it up!");
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
        outputBuffer.add("Alright, Goku! I've set the priority for this task:\n  " + task);
        outputBuffer.add("Now let's get it done before Buu gets bored! \uD83D\uDE0A");
    }

    /**
     * Displays the list of tasks that match the search keyword.
     *
     * @param tasks The list of tasks that match the search keyword.
     */
    public void showMatchingTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            outputBuffer.add("Goku, Buu couldn't find anything matching that search. Try something else?");
        } else {
            StringBuilder matchingTasksMessage = new StringBuilder("Here are the tasks that match what you're looking for:\n");
            for (int i = 0; i < tasks.size(); i++) {
                matchingTasksMessage.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
            }
            matchingTasksMessage.append("Nice work, Goku! Keep it up, Buu's watching! \uD83D\uDE0A");
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
