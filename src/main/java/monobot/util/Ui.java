package monobot.util;

import monobot.task.Task;

public class Ui {
    public void printGreeting() {
        printHorizontalLine();
        System.out.println("Hello! I'm MonoBot");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public void printFarewell() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public void printHorizontalLine() {
        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
    }

    public void showLoadingError() {
        printHorizontalLine();
        System.out.println("Error loading tasks from file.");
        printHorizontalLine();
    }

    public void printTasks(TaskList taskList) {
        printHorizontalLine();
        if (taskList.isEmpty()) {
            System.out.println("No tasks added yet");
        } else {
            System.out.println("Here are the tasks in your list");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, taskList.getTask(i));
            }
        }
        printHorizontalLine();
    }

    public void printAddedTask(Task task, int totalTasks) {
        printHorizontalLine();
        System.out.println("Added: " + task);
        System.out.println("Now you have " + totalTasks + " task(s) in the list");
        printHorizontalLine();
    }

    public void printDeletedTask(Task task, int totalTasks) {
        printHorizontalLine();
        System.out.println("Noted! I have removed this task:\n" + task);
        System.out.println("Now you have " + totalTasks + " task(s) in the list");
        printHorizontalLine();
    }

    public void printMarkedTask(Task task) {
        printHorizontalLine();
        System.out.println("Nice! I have marked this task as completed:\n" + task);
        printHorizontalLine();
    }

    public void printUnmarkedTask(Task task) {
        printHorizontalLine();
        System.out.println("Ok! I have marked this task as incomplete:\n" + task);
        printHorizontalLine();
    }

    public void printError(String message) {
        printHorizontalLine();
        System.out.println(message);
        printHorizontalLine();
    }
}
