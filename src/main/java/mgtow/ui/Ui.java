package mgtow.ui;

import mgtow.task.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the Ui in the Mgtow application.
 * This class provides methods for displaying responses to the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        printMsg(LINE);
        printMsg("Hello! I'm MGTOW.");
        printMsg("What can I do for you?");
        printMsg(LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        printMsg(LINE);
    }

    public void showLoadingError() {
        printMsg("No existing task file found. Starting with an empty task list.");
    }

    public void showError(String message) {
        printMsg(message);
    }

    public void showTasksOnDate(ArrayList<Task> tasks, LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        if (tasks.isEmpty()) {
            printMsg("No tasks found on " + date.format(formatter));
        } else {
            printMsg("Tasks on " + date.format(formatter) + ":");
            for (int i = 0; i < tasks.size(); i++) {
                printTask(i + 1, tasks.get(i));
            }
        }
    }

    /**
     * Displays the tasks found that match the given keyword.
     *
     * @param tasks The list of tasks that match the keyword.
     * @param keyword The keyword used for the search.
     */
    public void showFoundTasks(ArrayList<Task> tasks, String keyword) {
        if (tasks.isEmpty()) {
            printMsg("No matching tasks found for '" + keyword + "'.");
        } else {
            printMsg("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                printTask(i + 1, tasks.get(i));
            }
        }
    }

    public void showGoodbye() {
        printMsg("OK bye time to MGTOW.");
    }

    public void printMsg(String str) {
        System.out.println("\t" + str);
    }

    public void printTask(int ind, Task task) {
        System.out.println("\t" + ind + ". " + task);
    }
}