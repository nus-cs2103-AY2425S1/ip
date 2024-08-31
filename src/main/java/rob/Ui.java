package rob;

/**
 * Manages the user interface of the chatbot, including the text printed.
 */
public class Ui {
    private static final String GREET = "Hello! I'm Rob! What can I do for you?\n"
            + "List of commands\n"
            + "  - todo (desc)\n"
            + "  - deadline (desc) /by (deadline)\n"
            + "  - event (desc) /from (start) /to (end)\n"
            + "  - list\n"
            + "  - delete (task number)\n"
            + "  - mark (task number)\n"
            + "  - unmark (task number)\n"
            + "  - exit\n";
    private static final String EXIT = "Bye. Hope to see you again soon!";

    /**
     * Prints the error message if no file is loaded.
     *
     */
    public void showLoadingError() {
        System.out.println("No file loaded.");
    }

    /**
     * Returns the greeting message shown at the launch of chatbot
     *
     *   @return The greeting message string.
     */
    public String getGreet() {
        return GREET;
    }

    /**
     * Returns the exit message whenexit command is called
     *
     * @return The exit message string.
     */
    public String getExit() {
        return EXIT;
    }

    /**
     * Returns the tasks in the provided task list as a string.
     *
     * @param taskList The task list containing tasks to be displayed.
     * @return The list of tasks as a string.
     */
    public String showList(TaskList taskList) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < taskList.len(); i++) {
            list.append(i + 1).append(". ").append(taskList.getTask(i)).append("\n");
        }
        return "____________________________________\n"
                + list
                + "____________________________________\n";
    }

    /**
     * Returns the delete message.
     *
     * @param taskList The task list containing tasks to be deleted.
     * @param taskNum The task number of the task to be deleted.
     * @return The deleted task message.
     */
    public String delete(TaskList taskList, int taskNum) {
        // echo
        String echo = "____________________________________\n"
                + "deleted: " + taskList.getTask(taskNum - 1) + "\n"
                + "____________________________________\n";
        String numTaskInList = "Now you have " + (taskList.len() - 1) + " task(s) in the list.\n";
        return echo + numTaskInList;
    }

    /**
     * Returns the task added message when user adds a task.
     *
     * @param taskList The task list where tasks will be added.
     * @return The task number added message.
     */
    public String printText(TaskList taskList) {
        String echo = "____________________________________\n"
                + "added: " + taskList.getTask(taskList.len() - 1) + "\n"
                + "____________________________________\n";
        String numTaskInList = "Now you have " + taskList.len() + " task(s) in the list.\n";
        return echo + numTaskInList;
    }

    /**
     * Returns the task marked message.
     * If task is already marked, returns another specified string.
     *
     * @param taskList The task list containing tasks to be marked.
     * @param taskNum The task number of the task to be marked.
     * @return The task marked added message.
     */
    public String mark(TaskList taskList, int taskNum) {
        if (taskList.getTask(taskNum - 1).isDone) {
            return "____________________________________\n"
                    + "Already marked!\n"
                    + "____________________________________\n";
        } else {
            taskList.getTask(taskNum - 1).markAsDone();
            String marked = "____________________________________\n"
                    + "Nice! I've marked this task as done:\n" + taskList.getTask(taskNum - 1) + "\n"
                    + "____________________________________\n";
            return (marked);
        }
    }

    /**
     * Returns the task unmarked message.
     * If task is already unmarked, print another specified string.
     *
     * @param taskList The task list containing tasks to be unmarked.
     * @param taskNum The task number of the task to be unmarked.
     * @return The task marked added message.
     */
    public String unmark(TaskList taskList, int taskNum) {
        if (!taskList.getTask(taskNum - 1).isDone) {
            return "____________________________________\n"
                    + "Already unmarked!\n"
                    + "____________________________________\n";
        } else {
            taskList.getTask(taskNum - 1).unmark();
            String unmarked = "____________________________________\n"
                    + "OK, I've marked this task as not done yet:\n" + taskList.getTask(taskNum - 1) + "\n"
                    + "____________________________________\n";
            return unmarked;
        }
    }

}
