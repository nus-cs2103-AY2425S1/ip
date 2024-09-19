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

    /**
     * Prints out the list for the find command
     * @param list
     */
    public static void printFound(TaskList list) {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(list);
        printLine();
    }

    /**
     * Prints out help message
     */
    public static void printHelp() {
        printLine();
        System.out.println("Here are the commands you can use:");
        System.out.println("1. todo <description> - Adds a todo task to the list");
        System.out.println("2. deadline <description> /by <D/MM/YYY> <hhmm> - Adds a deadline task to the list");
        System.out.println("3. event <description> /from <D/MM/YYY> <hhmm> /to <hhmm> - Adds an event task to the list");
        System.out.println("4. list - Lists all tasks in the list");
        System.out.println("5. done <task number> - Marks a task as done");
        System.out.println("6. delete <task number> - Deletes a task from the list");
        System.out.println("7. find <keyword> - Finds tasks with the keyword");
        System.out.println("8. help - Shows the list of commands");
        System.out.println("9. bye - Exits the program");
        printLine();
    }

    /**
     * Prints out the unknown command message
     */
    public static void printUnkownCommand() {
        printLine();
        System.out.println("I'm sorry, but I don't know what that means :-(");
        System.out.println("Type 'help' to see the list of commands");
        printLine();
    }
}