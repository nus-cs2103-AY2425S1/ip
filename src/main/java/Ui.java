import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String BREAKLINE = "——————————————————————————";
    private final String LOGO = " ____        _           \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    
    public void showWelcomeMessage() {
        System.out.println(BREAKLINE);
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm ChattyBuddy");
        System.out.println("What can I do for you?");
        System.out.println(BREAKLINE);
    }

    public void showErrorMessage(String message) {
        System.out.println("error: " + message);
    }

    public void showGoodbyeMessage() {
        System.out.println(BREAKLINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BREAKLINE);
    }

    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d.[%s][%s] %s%n", i + 1, taskList.get(i).type, taskList.get(i).getStatusIcon(), taskList.get(i));
        }
    }

    public void showMarkTask(Task task) {
        String breakLine = "——————————————————————————";
        System.out.println(breakLine);
        System.out.println("OK, I've marked this task as done:");
        System.out.printf("[%s][%s] %s%n", task.type, task.getStatusIcon(), task);
        System.out.println(breakLine);
    }

    public void showUnmarkTask(Task task) {
        String breakLine = "——————————————————————————";
        System.out.println(breakLine);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("[%s][%s] %s%n", task.type, task.getStatusIcon(), task);
        System.out.println(breakLine);
    }

    public void showAddTask(Task task, int taskListSize) {
        String breakLine = "——————————————————————————";
        System.out.println(breakLine);
        System.out.println("Got it. I've added this task:");
        System.out.printf("  [%s][%s] %s%n", task.type, task.getStatusIcon(), task);
        System.out.printf("Now you have %d tasks in the list%n", taskListSize);
        System.out.println(breakLine);
    }

    public void showDeleteTask(Task task, int taskListSize) {
        String breakLine = "——————————————————————————";
        System.out.println(breakLine);
        System.out.println("Noted. I've removed this task:");
        System.out.printf("[%s][%s] %s%n", task.type, task.getStatusIcon(), task);
        System.out.printf("Now you have %d tasks in the list%n", taskListSize);
        System.out.println(breakLine);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
