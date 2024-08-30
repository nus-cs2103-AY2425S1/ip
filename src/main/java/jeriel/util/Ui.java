package jeriel.util;
import jeriel.command.*;
import jeriel.task.*;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints out a friendly welcome message to the user.
     * The message shows the logo of Jeriel, a greeting message, and
     * a question asking the user what they want to do.
     */
    public void showWelcome() {
        String logo = "     _  ____  ___   _  ____  _      \n"
                    + "    | || ___|| _ \\ | || ___|| |     \n"
                    + "    | |||___ ||_> || |||___ | |     \n"
                    + " _  | || ___||  _/ | || ___|| |     \n"
                    + "| |_| |||___ ||\\\\  | |||___ | |____ \n"
                    + " \\___/ |____||| \\\\ |_||____||______|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Jeriel");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads a line of text from the user and returns it as a string.
     * 
     * @return the line of text read from the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a line to the console to visually separate the command entry from the output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an error message when there is an error loading tasks from storage.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks.");
    }

    /**
     * Prints a goodbye message to the user when the user exits the app.
     */
    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Goodbye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message to the user when a task is added to the task list.
     * 
     * @param task the task that was added
     * @param size the new size of the task list
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message to the user when a task is deleted from the task list.
     * 
     * @param task the task that was deleted
     * @param size the new size of the task list
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the list of tasks to the user.
     * 
     * @param tasks the task list to print
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an error message to the user.
     * 
     * @param message the error message to print
     */
    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" OH NO DIOS MIOS!!! " + message);
        System.out.println("____________________________________________________________");
    }
}
