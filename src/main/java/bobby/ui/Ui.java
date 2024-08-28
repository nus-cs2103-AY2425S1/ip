package bobby.ui;

import bobby.exceptions.InvalidTaskNumberException;
import bobby.tasklist.TaskList;
import bobby.tasks.Task;

import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showGreeting() {
        String greeting = "Hello I'm Bobby\n" + "What can I do for you today?";
        System.out.println(greeting);
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Task added successfully:");
        System.out.println("  " + task);
        System.out.println(String.format("Now you have %d tasks in the list.", size));
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println("Task removed successfully:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done: " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet: " + task);
    }

    public void showTasks(TaskList taskList) throws InvalidTaskNumberException {
        if (taskList.isEmpty()) {
            System.out.println("No tasks added to the list yet.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(String.format("%d.%s", i + 1, taskList.get(i)));
            }
        }
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    public void showFindTasksMessage(LocalDate date) {
        System.out.println("Tasks on " + date + ":");
    }

    public void showNoTasksFound() {
        System.out.println("No tasks found for the given date.");
    }
}
