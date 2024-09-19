package megamind.ui;

import java.time.format.DateTimeParseException;

/**
 * The `Ui` class handles interactions with the user.
 * It provides methods to display messages, errors, and task-related information.
 * The class also includes methods to greet the user and show help information.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Greets the user.
     */
    public String greet() {
        String logo = """
                  _____\s
                 /     \\\s
                | () () |
                 \\  ^  /\s
                  |||||\s
                  |||||\s
                """;
        return HORIZONTAL_LINE + "Hello from\n" + logo + "What's good, I'm Megamind.\nWhat can I do for you?";
    }

    /**
     * Shows the task added message to the user.
     *
     * @param task      Task that has been added.
     * @param taskCount Number of tasks in the list.
     */
    public String showTaskAdded(String task, int taskCount) {
        return "Got it. I've added this task:\n" + task + "\n" + "Now you have " + taskCount + " tasks in the list.";
    }

    /**
     * Shows the task deleted message to the user.
     *
     * @param task Task that has been deleted.
     */
    public String showTaskDeleted(String task) {
        return "Task has been deleted successfully:\n" + task;
    }

    /**
     * Shows the mark/unmark task message to the user.
     *
     * @param task   Task that has been marked.
     * @param isDone Boolean value indicating if the task is done.
     */
    public String showMarkTask(String task, boolean isDone) {
        String status = isDone ? "Good job! You've completed a task:\n" : "Alright, I've marked it as not done:\n";
        return status + task;
    }

    /**
     * Shows the exit message to the user.
     */
    public String showExit() {
        return "See you around!";
    }

    /**
     * Shows an error message to the user based on the exception type.
     *
     * @param e The exception that was thrown.
     * @return A string containing the error message to be displayed to the user.
     */
    public String showErrorMessage(Exception e) {
        if (e instanceof DateTimeParseException) {
            return "Invalid date/time format. Please use the "
                   + "format: dd/MM/yyyy HHmm";
        } else {
            return e.getMessage();
        }
    }

    /**
     * Shows the help message to the user.
     */
    public String showHelp() {
        return """
                All dates and times should be in the format: dd/MM/yyyy HHmm \n
                Here are the commands you can use:
                1. list - List all tasks
                2. todo <description> - Add a to-do task
                3. deadline <description> /by <deadline> - Add a deadline task
                4. event <description> /from <start time> /to <end time> - Add an event task
                5. recur <description> /from <start time> /to <end time> /every <number of days> - Add an recurring task
                6. mark <task number> - Mark a task as done
                7. unmark <task number> - Mark a task as not done
                8. delete <task number> - Delete a task
                9. bye - Exit the program""";
    }
}
