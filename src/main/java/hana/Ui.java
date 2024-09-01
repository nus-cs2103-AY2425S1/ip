package hana;

import hana.task.Task;
import hana.task.TaskList;

import java.util.Scanner;
import java.util.List;

public class Ui {
    private final String line = "    ____________________________________________________________\n";
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(line + "    Hello I'm Hana\n" + "    What can I do for you?\n" + line);
    }

    public void showGoodbye() {
        System.out.println(line + "    Bye. Hope to see you again soon!\n" + line);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("    OOPS!!! Error loading the task list.");
    }

    public void showError(String message) {
        System.out.println(line + "    " + message + "\n" + line);
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(line + "    Got it. I've added this task:\n" +
                "      " + task + "\n" +
                "    Now you have " + taskCount + " tasks in the list.\n" + line);
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(line + "    Noted. I've removed this task:\n" +
                "      " + task + "\n" +
                "    Now you have " + taskCount + " tasks in the list.\n" + line);
    }

    public void showTaskMarked(Task task) {
        System.out.println(line + "    Nice! I've marked this task as done:\n" +
                "     " + task + "\n" + line);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(line + "    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(line);
    }

    public void showFindResults(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            System.out.println(line + "    No matching tasks found.\n" + line);
        } else {
            System.out.println(line + "    Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + foundTasks.get(i));
            }
            System.out.println(line);
        }
    }
}

