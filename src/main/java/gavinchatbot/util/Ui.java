package gavinchatbot.util;

import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Gavin's Chat Bot!\n");
        System.out.println("What can I do for you?\n");
        System.out.println(horizontalLine);
    }

    public void showGoodbye() {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    public void showList(TaskList tasks) throws GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        System.out.println(horizontalLine);
    }

    public void showMarkedTask(Task task) throws GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
        System.out.println(horizontalLine);

    }

    public void showUnmarkedTask(Task task) throws GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + task);
        System.out.println(horizontalLine);

    }

    public void showAddedTask(Task task, int size) throws GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    public void showDeletedTask(Task task, int size) {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    public void showError(String message) {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("!!!ERROR!!! " + message);
        System.out.println(horizontalLine);
    }

    public void showLoadingError() {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Error loading tasks from file. Starting with an empty task list.");
        System.out.println(horizontalLine);
    }

    public void showFoundTasks(ArrayList<Task> tasks) {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(horizontalLine);
    }
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
