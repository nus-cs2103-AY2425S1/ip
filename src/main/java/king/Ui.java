package king;

import king.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String LOGO = " _  __ _             \n" +
            "| |/ /(_)_ __   __ _ \n" +
            "| ' / | | '_ \\ / _` |\n" +
            "| . \\ | | | | | (_| |\n" +
            "|_|\\_\\|_|_| |_|\\__, |\n" +
            "               |___/ ";

    public void showWelcome() {
        System.out.println("Behold the wrath of the\n" + LOGO);
        showLine();
        System.out.println("What do you have for me?");
        showLine();
    }

    public void showGoodbye() {
        System.out.println("You are dismissed my humble servant.");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showTaskList(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + taskList.getTask(i).toString());
        }
        showLine();
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showTaskMarked(Task task) {
        System.out.println("Good job on the completion my minion!\n" + task.toString());
        showLine();
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("Better get to work before I execute you!\n" + task.toString());
        showLine();
    }

    public void showTaskAdded(Task task, int size) {
        showLine();
        System.out.println("Approved. I've added this to your list of duties:\n" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }
}
