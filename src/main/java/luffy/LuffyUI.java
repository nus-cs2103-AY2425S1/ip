package luffy;

import java.util.Scanner;

import static java.lang.String.format;

public class LuffyUI {

    private final Scanner commandScanner;

    public LuffyUI() {
        this.commandScanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcomeMessage() {
        showLine();
        System.out.println(" Hello! I'm luffy.Luffy");
        System.out.println(" What can I do for you?");
        showLine();
    }

    public void showMarkedTask(Task task) {
        showLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  " + task.stringIsDone());
        showLine();
    }

    public void showUnmarkedTask(Task task) {
        showLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("  " + task.stringIsDone());
        showLine();
    }

    public void showAddedTask(Task task, TaskList taskList) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + task.stringIsDone());
        System.out.println(String.format(" Now you have %s tasks in the list.", taskList.size()));
        showLine();
    }

    public void showDeletedTask(Task task, TaskList taskList) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + task.stringIsDone());
        System.out.println(String.format(" Now you have %s tasks in the list.", taskList.size()));
        showLine();
    }

    public void showExitMessage() {
        showLine();
        System.out.println(" Bye. Hope to see you again!");
        showLine();
    }

    public void showErrorMessage(String errorMsg) {
        System.out.println(errorMsg);
    }

    public String readNextCommand() {
        return commandScanner.nextLine();
    }

    public void displayTasks(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(format(" %d.%s", i + 1, taskList.getTask(i).stringIsDone()));
        }
    }
}
