package Papagu.Ui;

/**
 * The Ui class contains methods to print messages to the user.
 */
public class Ui {

    /**
     * Prints a line to the console.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the welcome message to the console.
     */
    public static void printWelcomeMessage() {
        printLine();
        System.out.println("Hello! I'm Papagu");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Prints the representation of the tasklist
     */
    public static void printList(TaskList list) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        System.out.println(list);
        printLine();
    }

    /**
     * Prints out marked message
     *
     * @param list
     * @param taskNumber
     */
    public static void printMarkAsDone(TaskList list, int taskNumber) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        printLine();
    }

    /**
     * prints out not marked message
     * @param list
     * @param taskNumber
     */
    public static void printMarkAsNotDone(TaskList list, int taskNumber) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        printLine();
    }

    /**
     * Prints the bye message
     */
    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints the delete message
     * @param task
     */
    public static void printDelete(Task task) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        printLine();
    }

    /**
     * prints the added message
     * use for all task types
     * @param task
     */
    public static void printAdded(Task task) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        printLine();
    }

    /**
     * prints the error message
     */
    public static void printLoadingError() {
        System.out.println("Error loading file");
    }
}