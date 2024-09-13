
package espresso.ui;
import espresso.ui.Ui;
import espresso.task.TaskList;
import espresso.task.Task;
import espresso.task.TodoTask;
import espresso.task.DeadlineTask;
import espresso.task.EventTask;
import espresso.storage.Storage;
import espresso.parser.Parser;
import espresso.command.InvalidCommandException;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void printLine() {
        System.out.println("________________________________________________");
    }

    public void printWelcome() {
        printLine();
        System.out.println("   ____                            \n" +
                "  / __/__ ___  _______ ___ ___ ___ \n" +
                " / _/(_-</ _ \\/ __/ -_|_-<(_-</ _ \\\n" +
                "/___/___/ .__/_/  \\__/___/___/\\___/\n" +
                "       /_/                         ");
        System.out.println("Hello! I'm Espresso");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void printGoodbye() {
        printLine();
        System.out.println("It was nice talking to you!");
        System.out.println("Until next time....");
        printLine();
    }

    public void printError(String message) {
        printLine();
        System.out.println("Error: " + message);
        printLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printTasks(TaskList taskList) throws InvalidCommandException {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.println(i + 1 + "." + taskList.getTask(i));
        }
        printLine();
    }

    public void printTaskAdded(Task task, String type) {
        printLine();
        System.out.println("Added the following " + type + ":");
        System.out.println(task);
        printLine();
    }

    public void printTaskRemoved(Task task) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        printLine();
    }

    public void printTaskMarked(Task task) {
        printLine();
        System.out.println("Nice. The following task has been marked as done:");
        System.out.println(task);
        printLine();
    }

    public void printTaskUnmarked(Task task) {
        printLine();
        System.out.println("Nice. The following task has been marked as undone:");
        System.out.println(task);
        printLine();
    }
}
