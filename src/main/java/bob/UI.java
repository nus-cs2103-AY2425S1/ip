package bob;

import java.util.Scanner;

import bob.tasks.Task;
import bob.tasks.TaskList;

/**
 * Manages all the User Interface actions such as printing and scanning
 */
public class UI {
    private static final String DIVIDER = "____________________________________________________________\n";

    /**
     * Reads the user input and returns it
     *
     * @return String of the command that the user entered
     */
    public static String readCommand() {
        return new Scanner(System.in).nextLine().trim().replace("  ", " ");
    }

    /**
     * Prints the dividing line
     */
    public static void printLine() {
        System.out.print(DIVIDER);
    }

    /**
     * Prints the greeting text when the program first launches
     */
    public static void printGreetings() {
        printLine();
        System.out.println("Hello! I'm Bob\n"
                    + "What can I do for you?");
        printLine();
    }

    /**
     * Prints the exit text when the program exits
     */
    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the taskList for the user to view
     *
     * @param taskList TaskList to be printed
     */
    public static void printTaskList(TaskList taskList) {
        System.out.println(taskList);
    }

    /**
     * Prints the task that was added into the taskList
     *
     * @param task Task that was added
     */
    public static void printAddTask(Task task) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + task);
    }

    /**
     * Prints the task that was deleted from the taskList
     *
     * @param task Task that was deleted
     */
    public static void printDeleteTask(Task task) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("\t" + task);
    }

    /**
     * Prints the text to show that a task has been marked
     */
    public static void printMarkTask() {
        System.out.println(" Noted. I've marked the task as completed:");
    }


    /**
     * Prints the text to show that a task has been unmarked
     */
    public static void printUnmarkTask() {
        System.out.println(" Noted. I've marked the task as uncompleted:");
    }

    /**
     * Prints the text to show the number of tasks in the TaskList currently
     *
     * @param taskList TaskList that is being used
     */
    public static void printCurrentTaskListStatus(TaskList taskList) {
        System.out.printf(" Now you have %d tasks in the list.%n", taskList.size());
    }

    /**
     * Prints a given message for the user
     *
     * @param message Message to be printed
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }
}
