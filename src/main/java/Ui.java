import java.util.Scanner;

public class Ui {
    public static String LINE = "\t" + "------------------------------------";

    public Ui() {
    }
    public static void printGreeting() {
        System.out.println(LINE);
        System.out.println("\t" + "Hello! I'm Pixel!");
        System.out.println("\t" + "What can I do for you?");
        System.out.println(LINE);
    }

    public static void printExit() {
        System.out.println(LINE);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void printList(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("\t" + "Here are the tasks in your list:");
        tasks.printTasks();
        System.out.println(LINE);
    }

    public static void printAddConfirmation(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t  " + tasks.getTask(tasks.getSize()-1).toString());
        System.out.println("\t" + "Now you have " + tasks.getSize() + " task(s) in the list.");
        System.out.println(LINE);
    }

    public static void printMarkConfirmation(Task currentTask) {
        System.out.println(LINE);
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println("\t  " + currentTask);
        System.out.println(LINE);
    }

    public static void printUnmarkConfirmation(Task currentTask) {
        System.out.println(LINE);
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println("\t  " + currentTask);
        System.out.println(LINE);
    }

    public static void printDeleteConfirmation(Task removedTask, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("\t" + "Noted. I've removed this task:");
        System.out.println("\t  " + removedTask);
        System.out.println("\t" + "Now you have " + tasks.getSize() + " task(s) in the list.");
        System.out.println(LINE);
    }

    public static String getCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void handleIndexOutOfBoundsException() {
        System.out.println(LINE);
        System.out.println("\t" + "I'm sorry, but there is no such index that exists");
        System.out.println("\t" + "Type in a valid index!");
        System.out.println(LINE);
    }

    public static void handleInvalidCommandException() {
        System.out.println(LINE);
        System.out.println("\t" + "I'm sorry, but I don't know what that means");
        System.out.println("\t" + "Type in a valid command!");
        System.out.println(LINE);
    }

    public static void handleEmptyDescriptionException() {
        System.out.println(LINE);
        System.out.println("\t" + "I'm sorry, but I can't add a task if the description is empty!");
        System.out.println("\t" + "Type in a valid description!");
        System.out.println(LINE);
    }

    public static void handleFileNotFoundException() {
        System.out.println(LINE);
        System.out.println("\t" + "I'm sorry, but I can't find the data for the ToDos!");
        System.out.println("\t" + "Please specify the correct file path.");
        System.out.println(LINE);
    }
}
