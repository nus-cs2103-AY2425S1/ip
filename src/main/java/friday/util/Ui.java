package friday.util;

import friday.task.Task;
import friday.task.TaskList;

import java.util.Scanner;

public class Ui {
    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("\tWelcome Back! I'm Friday.");
        System.out.println("\tWhat can I do for you today?");
        System.out.println("\tTo view the list of commands that I support, type 'help' for more information.");
        showLine();
    }

    public void showHelp() {
        System.out.println("\tHere are the list of commands that I support");
        System.out.println("\thelp - List of commands supported by me, Friday.");
        System.out.println("\tlist - View all entries to the current list of things" +
                " you have asked me to take note of.");
        System.out.println("\tmark <integer> - Mark an entry in the list as a completed task.");
        System.out.println("\tunmark <integer> - Unmark an entry in the list as a completed task.");
        System.out.println("\ttodo <string> - Remember a TODO friday.task.Task for you to revisit again.");
        System.out.println("\tdeadline <string> /by <yyyy-mm-dd hhmm> - Remember a friday.task.Deadline friday.task.Task for" +
                " you to complete by the deadline.");
        System.out.println("\tevent <string> /from <yyyy-mm-dd hhmm> /to <yyyy-mm-dd hhmm> - Remember an friday.task.Event friday.task.Task" +
                " from when it begins to when it ends.");
        System.out.println("\tdelete <integer> - Delete an entry from your current list.");
        System.out.println("\tbye - Exits this app and says Good Bye to Friday :)");
    }

    public void showLoadingError() {
        System.out.println("An error occurred while loading tasks.");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.getTasks().get(i).toString());
        }
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showGoodbye() {
        System.out.println("\tGood Bye. Hope to see you again soon!");
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
    }

    private final Scanner sc = new Scanner(System.in);
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "bye";
        }
    }
}
