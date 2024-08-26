package rob;

public class Ui {
    private static final String GREET = "Hello! I'm Rob\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!";

    /**
     * Prints the error message if no file is loaded.
     *
     */
    public void showLoadingError() {
        System.out.println("No file loaded.");
    }

    /**
     * Prints the greeting message.
     *
     */
    public void greet() {
        System.out.println(GREET);
    }

    /**
     * Prints the exit message.
     *
     */
    public void exit() {
        System.out.println(EXIT);
    }

    /**
     * Prints the tasks in the provided task list.
     *
     * @param taskList The task list containing tasks to be displayed.
     */
    public void showList(TaskList taskList) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskList.len(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTask(i));
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints the delete message.
     *
     * @param taskList The task list containing tasks to be deleted.
     * @param taskNum The task number of the task to be deleted.
     */
    public void delete(TaskList taskList, int taskNum) {
        // echo
        String echo = "____________________________________________________________\n" +
                "deleted: " + taskList.getTask(taskNum - 1) + "\n" +
                "____________________________________________________________\n";
        String numTaskInList = "Now you have " + (taskList.len() - 1) + " task(s) in the list.\n";
        System.out.println(echo + numTaskInList);
    }

    /**
     * Prints the task added message.
     *
     * @param taskList The task list where tasks will be added.
     */
    public void printText(TaskList taskList) {
        String echo = "____________________________________________________________\n" +
                "added: " + taskList.getTask(taskList.len() - 1) + "\n" +
                "____________________________________________________________\n";
        String numTaskInList = "Now you have " + taskList.len() + " task(s) in the list.\n";
        System.out.println(echo + numTaskInList);
    }

    /**
     * Prints the task marked message.
     * If task is already marked, print another specified string.
     *
     * @param taskList The task list containing tasks to be marked.
     * @param taskNum The task number of the task to be marked.
     */
    public void mark(TaskList taskList, int taskNum) {
        if (taskList.getTask(taskNum - 1).isDone) {
            System.out.println("Already done!");
        } else {
            taskList.getTask(taskNum - 1).markAsDone();
            String marked = "____________________________________________________________\n" +
                    "Nice! I've marked this task as done:\n" + taskList.getTask(taskNum - 1) + "\n" +
                    "____________________________________________________________\n";
            System.out.println(marked);
        }
    }

    /**
     * Prints the task unmarked message.
     * If task is already unmarked, print another specified string.
     *
     * @param taskList The task list containing tasks to be unmarked.
     * @param taskNum The task number of the task to be unmarked.
     */
    public void unmark(TaskList taskList, int taskNum) {
        if (!taskList.getTask(taskNum - 1).isDone) {
            System.out.println("Already unmarked!");
        } else {
            taskList.getTask(taskNum - 1).unmark();
            String unmarked = "____________________________________________________________\n" +
                    "OK, I've marked this task as not done yet:\n" + taskList.getTask(taskNum - 1) + "\n" +
                    "____________________________________________________________\n";
            System.out.println(unmarked);
        }
    }

}
