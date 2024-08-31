package orion.chatbot;

import orion.tasks.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {

    public static final String LOGO = "             .__               \n"
            + "  ___________|__| ____   ____  \n"
            + " /  _ \\_  __ \\  |/  _ \\ /    \\ \n"
            + "(  <_> )  | \\/  (  <_> )   |  \\\n"
            + " \\____/|__|  |__|\\____/|___|  /\n"
            + "                            \\/ \n";

    public static final String BAR = "______________________________________________\n";

    public static final String INDENT = "    ";

    private final Scanner sc;

    protected Ui() {
        sc = new Scanner(System.in);
    }

    protected void printBar() {
        System.out.println(Ui.BAR);
    }

    private void printIndent(String message) {
        System.out.println(Ui.INDENT + message);
    }

    public void printGreet() {
        printBar();
        System.out.println(Ui.LOGO);
        printBar();

        printIndent("Hello from Orion!");
        printIndent("We're fetching your task list from the cosmos...");
        printBar();
    }

    public void printGoodbye() {
        printIndent("Bye! Hope to see you again soon!");
    }

    protected String readCommand() {
        String input = sc.nextLine();
        printBar();
        return input;
    }

    public void printTaskList(List<String> taskDescriptions) {
        printIndent("Here are the tasks in your list:");
        for (String s : taskDescriptions) {
            printIndent(s);
        }
    }

    public void printAddTask(TaskList tasks, Task task) {
        printIndent("Sure! I've added the following task to your list:");
        printIndent(task.toString());
        printIndent("Now you have " + tasks.getNoTasks() + " tasks in your list.");
    }

    public void printDeleteTask(TaskList tasks, Task task) {
        printIndent("Sure! I've deleted the following task:");
        printIndent(task.toString());
        printIndent("Now you have " + tasks.getNoTasks() + " tasks in your list.");
    }

    public void printMarkTask(Task task) {
        printIndent("Sure! I've marked the following task as done:");
        printIndent(task.toString());
    }

    public void printUnmarkTask(Task task) {
        printIndent("Sure! I've marked the following task as undone:");
        printIndent(task.toString());
    }

    protected void printLoadTasks(int taskNo) {
        printIndent(String.format("Welcome back! You have %d tasks in your task list.", taskNo));
    }

    protected void printLoadingError() {
        printIndent("Your existing task list is somewhere amongst the stars...");
        printIndent("We can't seem to find it!");
        printIndent("We've created a new task list for you.");
    }

    public void printSaveTasks() {
        printIndent("Your task list has been saved successfully!");
    }

    public void printSaveError() {
        printIndent("We had a problem saving your task list... Sorry about that!");
    }

    public void printErrorMessage(String message) {
        printIndent(message);
    }

    public void closeScanner() {
        sc.close();
    }
}
