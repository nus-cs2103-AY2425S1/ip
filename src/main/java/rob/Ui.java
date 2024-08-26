package rob;

public class Ui {
    private static final String GREET = "Hello! I'm Rob\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!";

    public void showLoadingError() {
        System.out.println("No file loaded.");
    }

    public void greet() {
        System.out.println(GREET);
    }

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

    public void delete(TaskList taskList, int taskNum) {
        // echo
        String echo = "____________________________________________________________\n" +
                "deleted: " + taskList.getTask(taskNum - 1) + "\n" +
                "____________________________________________________________\n";
        String numTaskInList = "Now you have " + (taskList.len() - 1) + " task(s) in the list.\n";
        System.out.println(echo + numTaskInList);
    }

    public void printText(TaskList taskList) {
        String echo = "____________________________________________________________\n" +
                "added: " + taskList.getTask(taskList.len() - 1) + "\n" +
                "____________________________________________________________\n";
        String numTaskInList = "Now you have " + taskList.len() + " task(s) in the list.\n";
        System.out.println(echo + numTaskInList);
    }

    public void mark(TaskList taskList, int taskNum) {
        if (taskList.getTask(taskNum - 1).isDone) {
            System.out.println("Already done!");
        } else {
            String marked = "____________________________________________________________\n" +
                    "Nice! I've marked this task as done:\n" + taskList.getTask(taskNum - 1) + "\n" +
                    "____________________________________________________________\n";
            System.out.println(marked);
        }
    }

    public void unmark(TaskList taskList, int taskNum) {
        if (!taskList.getTask(taskNum - 1).isDone) {
            System.out.println("Already unmarked!");
        } else {
            String unmarked = "____________________________________________________________\n" +
                    "OK, I've marked this task as not done yet:\n" + taskList.getTask(taskNum - 1) + "\n" +
                    "____________________________________________________________\n";
            System.out.println(unmarked);
        }
    }

}
