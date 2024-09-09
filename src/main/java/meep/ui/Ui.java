package meep.ui;

/**
 * The {@code Ui} class handles the user interface interactions.
 * It provides methods to return messages and prompts to the user.
 */
public class Ui {

    /**
     * Returns a greeting message when the application starts.
     */
    public String greeting() {
        return """
                Hello! I'm Meep
                What can I do for you?
                """;
    }

    /**
     * Returns a farewell message when the user exits the application.
     */
    public String bye() {
        return "Bye! Have a great day!";
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public String doneTask(String task) {
        return """
                Nice! I've marked this task as done:
                """ + task + """
                """;
    }

    /**
     * Returns a message indicating that a task has been marked as undone.
     *
     * @param task The task that has been marked as not done.
     */
    public String undoneTask(String task) {
        return """
                OK, I've marked this task as not done yet:
                """ + task + """
                """;
    }

    /**
     * Returns a message indicating that a task has been added to the list.
     *
     * @param task The task that has been added.
     * @param size The current number of tasks in the list.
     */
    public String addTask(String task, int size) {
        return """
                Got it. I've added this task:
                """ + task + "\n"
                + "Now you have " + size + " tasks in the list\n";
    }

    /**
     * Returns a message indicating that a task has been deleted from the list.
     *
     * @param task The task that has been removed.
     * @param size The current number of tasks in the list after removal.
     */
    public String deleteTask(String task, int size) {
        return """
                Noted. I've removed this task:
                """ + task + "\n"
                + "Now you have "
                + (size - 1)
                + " tasks in the list\n";
    }

    /**
     * Returns the current list of tasks.
     *
     * @param tasks The formatted string of tasks.
     */
    public String listTasks(String tasks) {
        return """
                Here are the tasks in your list:
                """
                + tasks;
    }

    /**
     * Returns a message when an invalid command is entered.
     */
    public String invalidCommand() {
        return """
                Sorry, I don't understand that command. Please try again.
                """;
    }

    /**
     * Returns a message when an invalid todo command is entered.
     */
    public String invalidTodoCommand() {
        return """
                Sorry, there is something wrong with your todo command.
                Todo command should be in the format: todo <description>
                """;
    }

    /**
     * Returns a message when an invalid deadline command is entered.
     */
    public String invalidDeadlineCommand() {
        return """
                Sorry, there is something wrong with your deadline command.
                Deadline command should be in the format: deadline <description> /by <date>
                """;
    }

    /**
     * Returns a message when an invalid event command is entered.
     */
    public String invalidEventCommand() {
        return """
                Sorry, there is something wrong with your event command.
                Event command should be in the format: event <description> /from <date> /to <date>
                """;
    }

    /**
     * Returns a message when an invalid mark command is entered.
     */
    public String invalidMarkCommand() {
        return """
                Sorry, there is something wrong with your mark command.
                Mark command should be in the format: mark <index>
                The index should be a number within the range of the list
                """;
    }

    /**
     * Returns a message when an invalid unmark command is entered.
     */
    public String invalidUnmarkCommand() {
        return """
                Sorry, there is something wrong with your unmark command.
                Unmark command should be in the format: unmark <index>
                The index should be a number within the range of the list
                """;
    }

    /**
     * Returns a message when an invalid delete command is entered.
     */
    public String invalidDeleteCommand() {
        return """
                Sorry, there is something wrong with your delete command.
                Delete command should be in the format: delete <index>
                The index should be a number within the range of the list
                """;
    }

    /**
     * Returns a message when an invalid date format is entered.
     */
    public String invalidDateFormat() {
        return """
                Sorry, there is something wrong with the date format.
                Please use the format: d/M/yyyy HHmm
                """;
    }

    /**
     * Returns a message when an error occurs while loading tasks.
     */
    public String errorLoadingTask() {
        return """
                Error loading task: The saved task is in an invalid format.
                """;
    }

    /**
     * Returns a generic error message.
     */
    public String error() {
        return """
                An error occurred. Please try again.
                """;
    }

    /**
     * Returns a list of tasks that match the search criteria.
     * <p>
     * This method prints the tasks that match the search criteria, formatted with a header and footer.
     * </p>
     *
     * @param tasks The formatted string of tasks that match the search criteria.
     */
    public String findTasks(String tasks) {
        return """
                Here are the matching tasks in your list:
                """
                + tasks;
    }

    /**
     * Returns an error message for an invalid find command.
     */
    public String invalidFindCommand() {
        return """
                Sorry, there is something wrong with your find command.
                Find command should be in the format: find <keyword>
                """;
    }

}
