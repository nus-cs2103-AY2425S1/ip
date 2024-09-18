package bro.ui;

import bro.BroException;
import bro.task.Task;
import bro.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class encapsulates user interactions with bro.Bro.
 * It handles input/output operations like displaying messages to the user
 * and reading commands from the user.
 */
public class UI {
    // Static final strings for greeting and goodbye messages
    final static String GREETING_MESSAGE = """
            Hello! I'm bro.Bro
            What can I do for you?""";
    final static String GOODBYE_MESSAGE = "Goodbye.";

    // Scanner to read user input
    private final Scanner scanner;

    /**
     * Constructor that initializes the UI by setting up the Scanner for input.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the welcome message when the application starts.
     *
     * @return The greeting message.
     */
    public String showWelcome() {
        return reply(GREETING_MESSAGE);
    }

    /**
     * Shows the goodbye message when the application is about to close.
     *
     * @return The goodbye message.
     */
    public String showGoodbye() {
        return reply(GOODBYE_MESSAGE);
    }

    /**
     * Reads the next command inputted by the user.
     *
     * @return The command entered by the user.
     * @throws BroException If the input is empty or invalid.
     */
    public String readCommand() throws BroException {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            throw new BroException("Input cannot be empty.");
        }
    }

    /**
     * Displays a message indicating a task was successfully created.
     *
     * @param task          The task that was created.
     * @param numberOfTasks The current number of tasks in the list.
     * @return The success message.
     */
    public String showCreateTaskSuccess(Task task, int numberOfTasks) {
        return addTaskReply(task, numberOfTasks);
    }

    /**
     * Displays a message indicating a task was successfully marked as done.
     *
     * @param task The task that was marked as done.
     * @return The success message.
     */
    public String showMarkTaskSuccess(Task task) {
        return reply("Nice bro! I've marked this task as done:\n" + task);
    }

    /**
     * Displays a message indicating a task was successfully unmarked.
     *
     * @param task The task that was marked as undone.
     * @return The success message.
     */
    public String showUnmarkTaskSuccess(Task task) {
        return reply("Ok bro! I've marked this task as undone:\n" + task);
    }

    /**
     * Displays a message indicating a task was successfully deleted.
     *
     * @param task The task that was deleted.
     * @return The success message.
     */
    public String showDeleteTaskSuccess(Task task) {
        return reply("Noted. Removed this task:\n" + task);
    }

    /**
     * Displays a message showing the tasks found from a search.
     *
     * @param tasks The list of tasks that match the search criteria.
     * @return The search result message.
     */
    public String showTaskFind(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return reply("No tasks found.");
        }
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (Task task : tasks) {
            message.append(task.toString()).append("\n");
        }
        return reply(message.toString());
    }

    /**
     * Displays a separator line in the console.
     */
    public static void showLine() {
        String line = """
                ____________________________________________________________
                """;
        System.out.print(line);
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     * @return The error message string.
     */
    public String showError(String errorMessage) {
        return reply(errorMessage);
    }

    /**
     * Displays a message indicating a task was successfully added and shows the updated task count.
     *
     * @param task          The task that was added.
     * @param numberOfTasks The current number of tasks in the list.
     * @return The message indicating task was successfully added.
     */
    public static String addTaskReply(Task task, int numberOfTasks) {
        String replyStr = String.format("""
                ____________________________________________________________
                Got it. I've added this task:
                %s
                You now have %d tasks in the list.
                bro.Bro
                ____________________________________________________________
                """, task.toString(), numberOfTasks);
        System.out.print(replyStr);
        return replyStr;
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList The list of tasks to display.
     * @return The string representation of all tasks.
     */
    public String printTasks(TaskList taskList) {
        return taskList.printAllTasks();
    }

    /**
     * Prints a reply in a formatted manner with the bro.Bro signature.
     *
     * @param content The content to display as the reply.
     * @return The formatted reply string.
     */
    private String reply(String content) {
        String replyStr = String.format("""
                %s
                bro.
                """, content);
        System.out.print(replyStr);
        showLine();
        return replyStr;
    }
}
