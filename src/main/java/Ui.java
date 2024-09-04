import java.util.Scanner;
import java.util.zip.ZipFile;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Elara\nWhat can I do for you?";
        System.out.println(LINE + "\n" + welcomeMessage + "\n" + LINE);
    }

    public void showExitMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(LINE + "\n" + goodbyeMessage + "\n" + LINE);
    }

    public void showAddTaskMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list%n", taskList.getSize());
    }

    public void showNumOfTasksMessage(TaskList taskList) {
        System.out.printf("Now you have %d tasks in the list%n", taskList.getSize());
    }

    public void showMarkedTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarkedTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showRemoveTaskMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    public void listTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i) == null) {
                break;
            }
            System.out.println((i + 1) + ". " + taskList.getTask(i));
        }
    }

    public void showInvalidCommandMessage() {
        System.out.println("Invalid command. Please try again.");
    }

    public void showLoadingErrorMessage() {
        System.out.println("Sorry, but we are having trouble loading file.");
    }

    public void closeScanner() {
        scanner.close();
    }
}
