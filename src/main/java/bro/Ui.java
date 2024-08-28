package bro;

public class Ui {
    static final String line = "   ______________________________________________________\n";

    /**
     * Prints the status of the task being added, including the task details and the current total count of tasks.
     *
     * @param t The task that has been added.
     * @param i The total number of tasks currently in the list.
     */
    public void printStatus(Task t, int i) {
        System.out.println(line + "   Got it. I've added this task:");
        System.out.println("   " + t);
        System.out.printf("   Now you have %d tasks in the list\n%s", i, line);
    }

    /**
     * Prints a welcome message to the user.
     */
    public void printWelcome() {
        System.out.println(Ui.line + "   Hello! I'm Bro\n   What can I do for you?\n" + line);
    }

    /**
     * Prints the list of tasks currently stored, with each task numbered sequentially.
     *
     * @param tasks The TaskList object containing all tasks to be printed.
     */
    public void printList(TaskList tasks) {
        int len = tasks.size();
        System.out.print(line + "   Here are the tasks in your list:\n");
        for (int i = 0; i < len; i++) {
            System.out.printf("   %d.%s\n", i + 1, tasks.get(i));
        }
        System.out.print(line);
    }

    /**
     * Prints a default message when the user's input is not recognized or understood.
     */
    public void printDefault() {
        System.out.println(line + "   Well, what are u trying to do here? " +
                "I don't quite understand :(\n" + line);
    }

    /**
     * Prints an error message indicating the expected input format for commands that require a number.
     */
    public void printErrorFormat() {
        System.out.println(line + "   Please input a number after mark, " +
                "unmark or delete!!!\n" + line);
    }

    /**
     * Prints an error message when the user inputs a number outside the valid range of task indices.
     *
     * @param i The total number of tasks in the list, used to inform the user of the valid range.
     */
    public void printErrorSize(int i) {
        System.out.println(line + "   Input a valid number");
        System.out.printf("   You only have %d tasks in the list\n%s", i, line);
    }

    /**
     * Prints an exit message, signaling the end of the user's session with the chatbot.
     */
    public void printExit() {
        System.out.println(line + "   Bye. Hope to see you again soon!\n" + line);
    }

    /**
     * Prints a message indicating that a specified task has been marked as done.
     *
     * @param i      The index of the task in the list that has been marked as done.
     * @param tasks  The TaskList object containing all tasks, used to access the specific task.
     */
    public void printMark(int i, TaskList tasks) {
        System.out.print(line + "   Nice! I've marked this task as done:\n"
                + "   " + tasks.get(i - 1) + "\n" + line);
    }

    /**
     * Prints a message indicating that a specified task has been marked as not done.
     *
     * @param i      The index of the task in the list that has been unmarked.
     * @param tasks  The TaskList object containing all tasks, used to access the specific task.
     */
    public void printUnmark(int i, TaskList tasks) {
        System.out.print(line + "   OK, I've marked this task as not done yet:\n"
                + "   " + tasks.get(i - 1) + "\n" + line);
    }

    /**
     * Prints a message indicating that a specified task has been deleted from the list.
     *
     * @param i      The index of the task in the list that has been deleted.
     * @param tasks  The TaskList object containing all tasks, used to access the specific task.
     */
    public void printDelete(int i, TaskList tasks) {
        System.out.println(line + "   Noted. I've removed this task:");
        System.out.println("   " + tasks.get(i - 1));
        System.out.printf("   Now you have %d tasks in the list\n%s", tasks.size() - 1, line);
    }
}
