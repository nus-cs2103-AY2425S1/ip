package meep.ui;

/**
 * The {@code Ui} class handles the user interface interactions.
 * It provides methods to display messages and prompts to the user.
 */
public class Ui {

    /**
     * Displays a greeting message when the application starts.
     */
    public void greeting() {
        System.out.println("""
                -----------------------------------------
                Hello! I'm Meep
                What can I do for you?
                -----------------------------------------
                """);
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public void bye() {
        System.out.println("""
                -----------------------------------------
                Meep: Bye! Have a great day!
                -----------------------------------------
                """);
    }

    /**
     * Prompts the user to enter their input.
     */
    public void inputWaiting() {
        System.out.print("You: ");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void doneTask(String task) {
        System.out.println("""
                -----------------------------------------
                Meep: Nice! I've marked this task as done:
                """ + task + """
                -----------------------------------------
                """);
    }

    /**
     * Displays a message indicating that a task has been marked as undone.
     *
     * @param task The task that has been marked as not done.
     */
    public void undoneTask(String task) {
        System.out.println("""
                -----------------------------------------
                Meep: OK, I've marked this task as not done yet:
                """ + task + """
                -----------------------------------------
                """);
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that has been added.
     * @param size The current number of tasks in the list.
     */
    public void addTask(String task, int size) {
        System.out.println("""
                -----------------------------------------
                Meep: Got it. I've added this task:
                """ + task + "\n"
                + "Now you have " + size + " tasks in the list\n"
                + "-----------------------------------------"
        );
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task The task that has been removed.
     * @param size The current number of tasks in the list after removal.
     */
    public void deleteTask(String task, int size) {
        System.out.println("""
                -----------------------------------------
                Meep: Noted. I've removed this task:
                """ + task + "\n"
                + "Now you have "
                + (size - 1)
                + " tasks in the list\n"
                + "-----------------------------------------"
        );
    }

    /**
     * Displays the current list of tasks.
     *
     * @param tasks The formatted string of tasks.
     */
    public void listTasks(String tasks) {
        System.out.println("""
                -----------------------------------------
                Meep: Here are the tasks in your list:
                """
                + tasks
                + "-----------------------------------------"
        );
    }

    /**
     * Displays a message when an invalid command is entered.
     */
    public void invalidCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, I don't understand that command. Please try again.
                -----------------------------------------
                """);
    }

    /**
     * Displays a message when an invalid todo command is entered.
     */
    public void invalidTodoCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your todo command.
                Todo command should be in the format: todo <description>
                -----------------------------------------
                """);
    }

    /**
     * Displays a message when an invalid deadline command is entered.
     */
    public void invalidDeadlineCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your deadline command.
                Deadline command should be in the format: deadline <description> /by <date>
                -----------------------------------------
                """);
    }

    /**
     * Displays a message when an invalid event command is entered.
     */
    public void invalidEventCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your event command.
                Event command should be in the format: event <description> /from <date> /to <date>
                -----------------------------------------
                """);
    }

    /**
     * Displays a message when an invalid mark command is entered.
     */
    public void invalidMarkCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your mark command.
                Mark command should be in the format: mark <index>
                The index should be a number within the range of the list
                -----------------------------------------
                """);
    }

    /**
     * Displays a message when an invalid unmark command is entered.
     */
    public void invalidUnmarkCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your unmark command.
                Unmark command should be in the format: unmark <index>
                The index should be a number within the range of the list
                -----------------------------------------
                """);
    }

    /**
     * Displays a message when an invalid delete command is entered.
     */
    public void invalidDeleteCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your delete command.
                Delete command should be in the format: delete <index>
                The index should be a number within the range of the list
                -----------------------------------------
                """);
    }

    /**
     * Displays a message when an invalid date format is entered.
     */
    public void invalidDateFormat() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with the date format.
                Please use the format: d/M/yyyy HHmm
                -----------------------------------------
                """);
    }

    /**
     * Displays a message when an error occurs while loading tasks.
     */
    public void errorLoadingTask() {
        System.out.println("""
                -----------------------------------------
                Meep: Error loading task: The saved task is in an invalid format.
                -----------------------------------------
                """);
    }

    /**
     * Displays a generic error message.
     */
    public void error() {
        System.out.println("""
                -----------------------------------------
                Meep: An error occurred. Please try again.
                -----------------------------------------
                """);
    }

    /**
     * Displays a list of tasks that match the search criteria.
     * <p>
     * This method prints the tasks that match the search criteria, formatted with a header and footer.
     * </p>
     *
     * @param tasks The formatted string of tasks that match the search criteria.
     */
    public void findTasks(String tasks) {
        System.out.println("""
                -----------------------------------------
                Meep: Here are the matching tasks in your list:
                """
                + tasks
                + "-----------------------------------------"
        );
    }

    /**
     * Displays an error message for an invalid find command.
     */
    public void invalidFindCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your find command.
                Find command should be in the format: find <keyword>
                -----------------------------------------
                """);
    }

}
