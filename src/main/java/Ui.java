import java.util.Scanner;

public class Ui {

    private final Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    private static final String DIVIDER = "________________________________________\n";

    public void showWelcome() {
        System.out.println(DIVIDER + "Hello! I'm Downy.\nHow can I help?\n" + DIVIDER);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showExitMessage() {
        this.scanner.close();
        System.out.println(DIVIDER + "Bye! Yippee!");
    }

    public static void showErrorMessage(String message) {
        System.out.println(DIVIDER + "Error: " + message + "\n" + DIVIDER);
    }

    public static void showMessage(String message) {
        System.out.println(DIVIDER + message + "\n" + DIVIDER);
    }

    public void displayExitMessage() {
        System.out.println(DIVIDER + "Bye! Yippee!\n" + DIVIDER);
    }

    public void displayTasks(TaskList tasks) {
        System.out.printf(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        System.out.printf(DIVIDER);
    }

    public void displayCompletedTask(Task t) {
        System.out.println(DIVIDER + "Nice! You've completed this task:\n  " + t + "\n" + DIVIDER);
    }

    public void displayIncompleteTask(Task t) {
        System.out.println(DIVIDER + "Ok! This task is not complete:\n  " + t + "\n" + DIVIDER);
    }

    public void displayDeletedTask(Task t) {
        System.out.println(DIVIDER + "Ok! This task has been removed:\n " + t + "\n" + DIVIDER);
    }

    public void displayTaskAdded(Task t, int taskCount) {
        System.out.println(DIVIDER + "Okay! Added this task:\n  " + t
                + "\nNow you have " + taskCount + " tasks in this list\n" + DIVIDER);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void displayHelp() {
        System.out.print(DIVIDER);;
        System.out.println("Here are a list of valid commands:");
        System.out.println(" - list");
        System.out.println(" - mark <taskNumber>");
        System.out.println(" - unmark <taskNumber>");
        System.out.println(" - delete <taskNumber>");
        System.out.println(" - todo <taskDescription>");
        System.out.println(" - deadline <taskDescription> /by <dueDate>");
        System.out.println(" - event <taskDescription> /from <startTime> /to <endTime>");
        System.out.println(" - bye");
        System.out.println(" - help");
        System.out.println(DIVIDER);
    }
}
