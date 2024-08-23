package citadel.ui;
import java.util.Scanner;
import citadel.Task.TaskList;
import citadel.Task.Task;
import citadel.exception.CitadelException;

public class TextUI {
    private static final String NAME = "citadel";
    private static final String INTRO = "Hello! I'm " + NAME + "\n";
    private static final String QUESTION = "What can I do for you?\n";

    private static String GOODBYE = "Bye. Hope to see you again soon!\n";
    private final Scanner SCANNER = new Scanner(System.in);

    public TextUI() {
    }

    public String nextLine() {
        return this.SCANNER.nextLine();
    }

    public void printStart() {
        String start = INTRO + QUESTION;
        System.out.println(start);
    }

    public void printTasks(TaskList items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).printTask());
        }
    }

    public void printTaskCount(TaskList items) {
        System.out.println("Now you have " + items.size()
                            + " tasks in the list");
    }
    public void printCitadelException(CitadelException e) {
        System.out.println(e.toString());
    }
    public void printDateTimeParseException() {
        System.out.println("Incorrect Date Format! Please write the date "
                            + "in this format: dd/MM/yyyy HH:mm!");
    }
    public void printException(Exception e) {
        System.out.println("Error occurred: "
                            + e.getMessage());
    }
    public void printGoodbye() {
        System.out.println(GOODBYE);
    }
    public static void printTask(Task t, TaskList items) {
        System.out.println("Got it! I have added: " + t);
        System.out.println("Now you have " + items.size()
                            + " tasks in the list");
    }

    public static void printMark(TaskList tasks, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1));
    }
    public static void printUnmark(TaskList tasks, int index) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1));
    }
    public static void printDelete(TaskList tasks, Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + tasks.size()
                            + " tasks in the list");
    }
    public static void printFind(TaskList tasks) {
        System.out.println(" Here are the matching tasks in your list:");
        new TextUI().printTasks(tasks);
    }
}


