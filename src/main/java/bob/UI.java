package bob;

import bob.tasks.Task;
import bob.tasks.TaskList;

import java.util.Scanner;

public class UI {
    
    private static final String DIVIDER = "____________________________________________________________\n";

    public static String readCommand() {
        return new Scanner(System.in).nextLine().trim().replace("  ", " ");
    }

    public static void printLine() {
        System.out.print(DIVIDER);
    }

    public static void printGreetings() {
        printLine();
        System.out.println("Hello! I'm Bob.Bob\n"
                    + "What can I do for you?");
        printLine();
    }

    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printTaskList(TaskList taskList) {
        System.out.println(taskList);
    }

    public static void printAddTask(Task task) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + task);
    }

    public static void printDeleteTask(Task task) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("\t" + task);
    }

    public static void printMarkTask() {
        System.out.println(" Noted. I've marked the task as completed:");
    }

    public static void printUnmarkTask() {
        System.out.println(" Noted. I've marked the task as uncompleted:");
    }

    public static void printCurrentTaskListStatus(TaskList taskList) {
        System.out.printf(" Now you have %d tasks in the list.%n", taskList.size());
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
