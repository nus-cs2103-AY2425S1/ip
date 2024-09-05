package sage;

import sage.exception.SageException;
import sage.task.TaskList;
import sage.task.Task;

import java.util.Scanner;

/**
 * Represents the user interface of the task management application
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        return scanner.nextLine().trim();
    }

    public void closeScanner() {
        this.scanner.close();
    }

    /**
     * Display a welcome message to the users when the application starts
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Sage\n" + "How can I help you today? ;)";
    }

    /**
     * Display a goodbye message to the users when they exit the application
     */
    public String showGoodbyeMessage() {
        return " " + "Bye!! Hope you come and visit again soon!";
    }


    public String showErrorMessage(SageException e) {
        return e.toString();
    }

                                 /**
     * Display the list of tasks to the user
     *
     * @param tasks The TaskList object containing the tasks to be displayed
     */
    public String showTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There are no tasks in your list";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return "Here are the tasks in your list:\n" + sb;
    }

    public String showMarkedTask(Task task) {
        return "Great Job! I have marked this task as done:\n" + task;
    }

    public String showUnmarkedTask(Task task) {
        return "OK, please remember to complete this task later:\n" + task;
    }

    public String showAddedTask(Task task, int numberOfTasks) {
        return "Great! I will add this task to the list:\n" + task +
                "\nNow you have " + numberOfTasks +
                (numberOfTasks > 1 ? " tasks" : " task") + " in your list";
    }

    public String showDeletedTask(Task task, int numberOfTasks) {
        return "OK! I will remove this task:\n" + task +
                "\nNow you have " + numberOfTasks +
                (numberOfTasks > 1 ? " tasks" : " task") + " in your list";
    }

    public String showSearchedTask(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There are no matching tasks";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return "Here are the matching tasks in your list:\n" + sb;
    }
}
