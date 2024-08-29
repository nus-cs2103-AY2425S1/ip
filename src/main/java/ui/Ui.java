package ui;

import tasks.Task;
import tasks.TaskList;

import java.util.Scanner;

public class Ui {

    private final Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    private static final String DIVIDER = "________________________________________\n";

    public void showWelcome() {
        System.out.println(DIVIDER + "Hello! I'm Downy.\nHow can I help?\n" + DIVIDER);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showExitMessage() {
        this.scanner.close();
        System.out.println(DIVIDER + "Bye! Yippee!");
    }

    public static void showErrorMessage(String message) {
        System.out.println(DIVIDER + "Error: " + message + "\n" + DIVIDER);
    }

    public static void showMessage(String message) {
        System.out.println(DIVIDER + message + "\n" + DIVIDER);
    }

    public void displayExitMessage() {
        System.out.println(DIVIDER + "Bye! Yippee!\n" + DIVIDER);
    }

    public void displayTasks(TaskList tasks) {
        System.out.printf(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        System.out.printf(DIVIDER);
    }

    /**
     * Displays tasks from the task list that contain the specified keyword in their names.
     * The search is case-insensitive, and only matching tasks are displayed. If no tasks
     * match the keyword, a message indicating that no matching tasks were found is shown.
     *
     * @param tasks   The {@code TaskList} containing the tasks to be searched.
     * @param keyword The keyword to search for within the task names.
     */
    public void displayMatchingTasks(TaskList tasks, String keyword) {
        System.out.printf(DIVIDER);
        System.out.println("Here are the tasks in your list that match the keyword:");
        String lowerCaseKeyword = keyword.toLowerCase();
        int matchCount = 0;
        for (int i = 0; i < tasks.getSize(); i++) {
            String taskName = tasks.getTask(i).getName().toLowerCase();

            if (taskName.contains(lowerCaseKeyword)) {
                System.out.println((matchCount + 1) + ". " + tasks.getTask(i));
                matchCount++;
            }
        }
        if (matchCount == 0) {
            System.out.println("No matching tasks found.");
        }

        System.out.printf(DIVIDER);
    }


    public void displayCompletedTask(Task t) {
        System.out.println(DIVIDER + "Nice! You've completed this task:\n  " + t + "\n" + DIVIDER);
    }

    public void displayIncompleteTask(Task t) {
        System.out.println(DIVIDER + "Ok! This task is not complete:\n  " + t + "\n" + DIVIDER);
    }

    public void displayDeletedTask(Task t) {
        System.out.println(DIVIDER + "Ok! This task has been removed:\n  " + t + "\n" + DIVIDER);
    }

    public void displayTaskAdded(Task t, int taskCount) {
        System.out.println(DIVIDER + "Okay! Added this task:\n  " + t
                + "\nNow you have " + taskCount + " tasks in this list\n" + DIVIDER);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void displayHelp() {
        System.out.print(DIVIDER);;
        System.out.println("Here are a list of valid commands:");
        System.out.println(" - list");
        System.out.println(" - mark <taskNumber>");
        System.out.println(" - unmark <taskNumber>");
        System.out.println(" - delete <taskNumber>");
        System.out.println(" - todo <taskDescription>");
        System.out.println(" - deadline <taskDescription> /by <dueDate>");
        System.out.println(" - event <taskDescription> /from <startTime> /to <endTime>");
        System.out.println(" - bye");
        System.out.println(" - help");
        System.out.println(DIVIDER);
    }
}
