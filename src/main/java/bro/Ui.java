package bro;

public class Ui {
    static final String line = "   ______________________________________________________\n";

    /**
     * Returns the status of the task being added, including the task details and the current total count of tasks.
     *
     * @param t The task that has been added.
     * @param i The total number of tasks currently in the list.
     * @return The status of the TaskList
     */
    public String printStatus(Task t, int i) {
        return "   Got it. I've added this task:\n" +
                "   " + t + "\n" +
                "   Now you have " + i + " tasks in the list\n";
    }

    /**
     * @return A welcome message to the user.
     */
    public String printWelcome() {
        return "   Hello! I'm Bro\n   What can I do for you?\n";
    }

    /**
     * Returns the list of tasks currently stored, with each task numbered sequentially.
     *
     * @param tasks The TaskList object containing all tasks to be printed.
     * @return List of tasks currently stored
     */
    public String printList(TaskList tasks) {
        int len = tasks.size();
        StringBuilder output = new StringBuilder("   Here are the tasks in your list:\n");
        for (int i = 0; i < len; i++) {
            output.append(String.format("   %d.%s\n", i + 1, tasks.get(i)));
        }
        return output.toString();
    }

    /**
     * Returns String of tasks from the given task list that contain a specified keyword.
     *
     * @param tasks The list of tasks to search through.
     * @param s The keyword to search for in the task descriptions.
     * @return String of all tasks that contain a specific keyword
     */
    public String printByWord(TaskList tasks, String s) {
        StringBuilder output = new StringBuilder("   Here are the matching tasks in your list:\n");
        int len = tasks.size();
        int count = 1;
        for (int i = 0; i < len; i++) {
            if (tasks.get(i).toString().contains(s)) {
                output.append(String.format("   %d.%s\n", count, tasks.get(i)));
                count++;
            }
        }
        return output.toString();
    }

    /**
     * @return A default message when the user's input is not recognized or understood.
     */
    public String printDefault() {
        return "   Well, what are u trying to do here? " +
                "I don't quite understand :(\n";
    }

    /**
     * @return An error message indicating the expected input format for commands that require a number.
     */
    public String printErrorFormat() {
        return "   Please input a number after mark, " +
                "unmark or delete!!!\n";
    }

    /**
     * @return An error message when the user inputs a number outside the valid range of task indices.
     *
     * @param i The total number of tasks in the list, used to inform the user of the valid range.
     */
    public String printErrorSize(int i) {
        return "   Input a valid number\n" +
               "   You only have " + i + " tasks in the list\n";
    }

    /**
     * Returns a message indicating that a specified task has been marked as done.
     *
     * @param i      The index of the task in the list that has been marked as done.
     * @param tasks  The TaskList object containing all tasks, used to access the specific task.
     * @return A confirmation message
     */
    public String printMark(int i, TaskList tasks) {
        return "   Nice! I've marked this task as done:\n"
                + "   " + tasks.get(i - 1) + "\n";
    }

    /**
     * Returns a message indicating that a specified task has been marked as not done.
     *
     * @param i      The index of the task in the list that has been unmarked.
     * @param tasks  The TaskList object containing all tasks, used to access the specific task.
     * @return A confirmation message
     */
    public String printUnmark(int i, TaskList tasks) {
        return "   OK, I've marked this task as not done yet:\n"
                + "   " + tasks.get(i - 1) + "\n";
    }

    /**
     * Returns a message indicating that a specified task has been deleted from the list.
     *
     * @param i      The index of the task in the list that has been deleted.
     * @param tasks  The TaskList object containing all tasks, used to access the specific task.
     * @return A confirmation message
     */
    public String printDelete(int i, TaskList tasks) {
        return "   Noted. I've removed this task:\n" +
        "   " + tasks.get(i - 1) + "\n" +
        String.format("   Now you have %d tasks in the list\n", tasks.size() - 1);
    }
}
