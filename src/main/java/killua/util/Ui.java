package killua.util;

import killua.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private static final String LINE = "______________________________________________________________________________";
    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcomeMessage() {
        printLine();
        System.out.println("Welcome to Killua Task Manager!");
        System.out.println("Here are some commands you can use:");
        System.out.println("  bye - Exit the application");
        System.out.println("  list - List all tasks");
        System.out.println("  mark <task number> - Mark a task as done");
        System.out.println("  unmark <task number> - Mark a task as not done yet");
        System.out.println("  delete <task number> - Delete a task");
        System.out.println("  todo <description> - Add a new todo task");
        System.out.println("  deadline <description> /by <yyyy-mm-dd> OR ");
        System.out.println("  deadline <description> /by <yyyy-mm-dd hh:mm> - Add a new deadline task");
        System.out.println("  event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd> OR ");
        System.out.println("  event <description> /from <yyyy-mm-dd hh:mm> /to <yyyy-mm-dd hh:mm> - Add a new event task");
        printLine();
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void showLoadingError() {
        printLine();
        System.out.println("Error loading tasks.");
        printLine();
    }

    public void showTaskAdded(Task task, int taskCount) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    public void showTaskDeleted(Task task) {
        printLine();
        System.out.println("OK, I've deleted this task:");
        System.out.println("  " + task);
        printLine();
    }

    public void showBye() {
        printLine();
        System.out.println("Bye!");
        printLine();
    }

    public void showTaskList(TaskList tasks) {
        printLine();
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            tasks.printTasks();
        }
        printLine();
    }

    public void showTaskMarked(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printLine();
    }

    public void showTaskUnmarked(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        printLine();
    }

    public void showTasksOnDate(TaskList tasks, LocalDate date) {
        printLine();
        System.out.println("Tasks on " + date + ":");

        boolean hasTasks = false;
        for (Task task : tasks.getTasksOnDate(date).getTasks()) {
            System.out.println("  " + task);
            hasTasks = true;
        }

        if (!hasTasks) {
            System.out.println("No tasks found for this date.");
        }

        printLine();
    }

    public void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public String readCommand() {
        return in.nextLine().trim();
    }
}
