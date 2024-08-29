package seedu.avo.ui;

import java.util.List;
import java.util.Scanner;

import seedu.avo.tasks.Task;

public class AppUI {
    private final Scanner scanner;
    public AppUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message
     */
    public void showWelcome() {
        String greetingMessage = "Hello, I am Avo.\nWhat can I do for you?";
        System.out.println(greetingMessage);
    }
    /**
     * Displays an exit message
     */
    public void showExit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    /**
     * Displays an error message
     */
    public void showError(String message) {
        System.out.println(message);
    }
    /**
     * Reads user input from CLI
     * @return A raw input from the user
     */
    public String readInput() {
        String input = "exit";
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }
        return input;
    }
    private void print(String str) {
        System.out.println("         " + str);
    }
    private String getTask(List<Task> tasks, int index) {
        return index + 1 + ". " + tasks.get(index);
    }

    public void printTaskCount(int count) {
        if (count == 0) {
            print("You have no tasks.");
        } else if (count == 1) {
            print("You have one task.");
        } else {
            print(String.format("You have %s tasks.", count));
        }
    }
    public void printTasksFromList(List<Task> tasks, List<Integer> indexes) {
        for (Integer index: indexes) {
            print(getTask(tasks, index));
        }
    }
    public void printTask(List<Task> tasks, int index) {
        print(getTask(tasks, index));
    }
    public void printTaskMarked(List<Task> tasks, int index) {
        print("Nice! I've marked this task as done:");
        printTask(tasks, index);
    }
    public void printTaskUnmarked(List<Task> tasks, int index) {
        print("OK, I've marked this task as not done yet:");
        printTask(tasks, index);
    }
    public void printTaskAdded(List<Task> tasks) {
        print("Got it. I've added this task:");
        printTask(tasks, tasks.size() - 1);
    }
    public void printTaskRemoved(Task task) {
        print("Noted. I've removed this task:");
        print(task.toString());
    }
}
