package hana.ui;

import java.util.ArrayList;

import hana.HanaException;
import hana.task.Task;

/**
 * Handles interactions with the user in a GUI environment.
 */
public class Ui {
    private static final String NAME = "Hana";
    private StringBuilder responseBuilder;

    /**
     * Constructs a new Ui.
     * Initializes the response builder.
     */
    public Ui() {
        responseBuilder = new StringBuilder();
    }

    /**
     * Retrieves the accumulated response and clears the buffer.
     *
     * @return The accumulated response.
     */
    public String getResponse() {
        String response = responseBuilder.toString();
        responseBuilder.setLength(0); // Clear the response builder
        return response;
    }

    /**
     * Prints greetings.
     */
    public void printGreetings() {
        responseBuilder.append(" Hello! I'm ").append(NAME).append(".\n");
        responseBuilder.append(" What can I do for you?\n");
    }

    /**
     * Prints bye message.
     */
    public void printBye() {
        responseBuilder.append("Bye. The application will close in 3 seconds.\n");
        responseBuilder.append("Hope to see you again soon!\n");
    }

    /**
     * Prints error message.
     */
    public void printError(String message) {
        responseBuilder.append("Error: ").append(message).append("\n");
    }

    /**
     * Prints message after task successfully added.
     */
    public void printAdd(Task task, int taskCount) {
        responseBuilder.append("Got it. I've added this task:\n");
        responseBuilder.append("    ").append(task).append("\n");
        responseBuilder.append("Now you have ").append(taskCount).append(" tasks in the list.\n");
    }

    /**
     * Prints message after task successfully marked.
     */
    public void printMarked(Task task, boolean isDone) {
        if (isDone) {
            responseBuilder.append("Nice! I've marked this task as done:\n");
        } else {
            responseBuilder.append("OK, I've marked this task as not done yet:\n");
        }
        responseBuilder.append("  ").append(task).append("\n");
    }

    /**
     * Prints message after task successfully deleted.
     */
    public void printDeleted(Task task, int taskCount) {
        responseBuilder.append("Noted. I've removed this task:\n");
        responseBuilder.append("    ").append(task).append("\n");
        responseBuilder.append("Now you have ").append(taskCount).append(" tasks in the list.\n");
    }

    /**
     * Prints all tasks.
     */
    public void printTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            responseBuilder.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
    }

    /**
     * Prints any message.
     *
     * @param message Message to print.
     */
    public void printMessage(String message) {
        responseBuilder.append(message).append("\n");
    }

    /**
     * Prints all commands.
     */
    public void printCommands() {
        responseBuilder.append("""
                            Here are some examples of what you can do:
                            1. List all tasks: list
                            2. Mark a task as done: mark [task number]
                            3. Unmark a task: unmark [task number]
                            4. Add a todo: todo [description]
                            5. Add a deadline: deadline [description] /by [d/M/yyyy HHmm]
                            6. Add an event: event [description] /from [d/M/yyyy HHmm] /to [d/M/yyyy HHmm]
                            7. Delete a task: delete [task number]
                            8. Find By Date: findByDate [d/M/yyyy]
                            9. Find By Keyword: findByKey [keyword]
                            """);
    }

    /**
     * Prints the tasks that match the search criteria.
     *
     * @param tasks The list of tasks to print.
     */
    public void printTasksFound(ArrayList<Task> tasks) throws HanaException {
        if (tasks.isEmpty()) {
            responseBuilder.append("No tasks found with the given keyword.\n");
        } else {
            responseBuilder.append("Here are the tasks that match your search:\n");
            for (Task task : tasks) {
                responseBuilder.append(task).append("\n");
            }
        }
    }

    /**
     * Prints message after task successfully marked.
     */
    public void printSetPriority(Task task) {
        responseBuilder.append("OK, I've set this task to priority ").append(task.getPriority()).append(":\n");
        responseBuilder.append("  ").append(task).append("\n");
    }
}
