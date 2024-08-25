import java.util.Scanner;

public class Ui {

    private static final String BAR = "____________________________________________________________";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println(BAR);
        System.out.println("Hello! I'm Lama");
        System.out.println("What can I do for you?");
        System.out.println(BAR + "\n");
    }


    public void showAddCommand(TaskList taskList) {
        System.out.println(BAR);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(BAR + "\n");
    }

    public void showDeleteCommandHeader() {
        System.out.println(BAR);
        System.out.println("Noted. I've removed this task:");
    }

    public void showDeleteCommandFooter(TaskList taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(BAR + "\n");
    }

    public void showExitCommand() {
        System.out.println(BAR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BAR);
    }

    public void showMarkCommand(TaskList taskList, int indexOfMark) {
        System.out.println(BAR);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.get(indexOfMark));
        System.out.println(BAR + "\n");
    }

    public void showUnmarkCommand(TaskList taskList, int indexOfUnmark) {
        System.out.println(BAR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskList.get(indexOfUnmark));
        System.out.println(BAR + "\n");
    }

    public void showListCommand(TaskList taskList) {
        System.out.println(BAR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
        System.out.println(BAR + "\n");
    }

    public void showLoadingError() {
        System.out.println(BAR);
        System.out.println("Sorry, there's error loading the file!");
        System.out.println("Please Try Again!");
        System.out.println(BAR + "\n");
    }

    public void showError(String error) {
        System.out.println(BAR);
        System.out.println(error);
        System.out.println(BAR + "\n");
    }

}
