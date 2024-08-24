public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Greets the user.
     */
    public void greet() {
        String logo = """
                  _____\s
                 /     \\\s
                | () () |
                 \\  ^  /\s
                  |||||\s
                  |||||\s
                """;
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello from\n" + logo);
        System.out.println("What's good, I'm Megamind.\nWhat can I do for you?");
    }

    /**
     * Shows the line separator.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows the message to the user.
     *
     * @param message Message to be shown to the user.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Shows the error message to the user.
     *
     * @param errorMessage Error message to be shown to the user.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Shows the task added message to the user.
     *
     * @param task Task that has been added.
     * @param taskCount Number of tasks in the list.
     */
    public void showTaskAdded(String task, int taskCount) {
        System.out.println("Got it. I've added this task:\n" + task + "\n" +
                "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows the task deleted message to the user.
     *
     * @param task Task that has been deleted.
     */
    public void showTaskDeleted(String task) {
        System.out.println("Task has been deleted successfully:\n" + task);
    }

    /**
     * Shows the mark/unmark task message to the user.
     *
     * @param task Task that has been marked.
     * @param isDone Boolean value indicating if the task is done.
     */
    public void showMarkTask(String task, boolean isDone) {
        String status = isDone ? "Good job! You've completed a task:\n" : "Alright, I've marked it as not done:\n";
        System.out.println(status + task);
    }

    /**
     * Shows the exit message to the user.
     *
     */
    public void showExit() {
        System.out.println("See you around!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows the help message to the user.
     *
     */
    public void showHelp() {
        System.out.println("""
                All dates and times should be in the format: dd/MM/yyyy HHmm

                Here are the commands you can use:
                1. list - List all tasks
                2. todo <description> - Add a to-do task
                3. deadline <description> /by <deadline> - Add a deadline task
                4. event <description> /from <start time> /to <end time> - Add an event task
                5. mark <task number> - Mark a task as done
                6. unmark <task number> - Mark a task as not done
                7. delete <task number> - Delete a task
                8. bye - Exit the program""");
    }
}
