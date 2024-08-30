package snowy;

/**
 * Represents the main object which prints out the user interface.
 */
public class Ui {
    private static final String LINE = "-----------------------------------\n";

    private static final String GREETING = LINE
            + "Hello! My name is Snowy\n"
            + "What can I do for you?\n"
            + LINE;

    private static final String ENDING = "Bye! Hope to see you again soon!\n"
            + LINE;

    public void printLine() {
        System.out.print(LINE);
    }

    public void printGreeting() {
        System.out.print(GREETING);
    }

    public void printEnding() {
        System.out.print(ENDING);
    }

    public void printIndexError() {
        System.out.println("Invalid index input. Please try again.");
    }

    public void printTodoFormatError() {
        System.out.println("Invalid input for todo. Make sure that you are following the correct format.");
        System.out.println("Example:");
        System.out.println("todo Read Book");
    }

    public void printDeadlineFormatError() {
        System.out.println("Invalid input for Deadline. Make sure that you are following the correct format.");
        System.out.println("Example:");
        System.out.println("deadline Return Book /by 2024-09-01");
    }

    public void printEventFormatError() {
        System.out.println("Invalid input for Event. Make sure that you are following the correct format.");
        System.out.println("Example:");
        System.out.println("event Orientation Camp /from 2024-09-01 /to 20224-09-04");
    }

    public void printUnknownCommand() {
        System.out.println("Invalid command given. Please try again.");
    }

    public void printList(TaskList tasks) {
        System.out.print(tasks.toString());
    }

    public void printMarkDone(Task task) {
        System.out.println("Ok, I've marked this task as completed:");
        System.out.println(task.toString());
    }

    public void printMarkIncomplete(Task task) {
        System.out.println("Ok, I've marked this task as incomplete:");
        System.out.println(task.toString());
    }

    public void printDeleteTask(Task task) {
        System.out.println("Ok, I've deleted this task:");
        System.out.println(task.toString());
    }
}
