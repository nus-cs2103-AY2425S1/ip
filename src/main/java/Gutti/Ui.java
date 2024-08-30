package Gutti;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Ui} class handles interactions with the user.
 * It provides methods to display messages and prompts.
 */
public class Ui {

    /**
     * Displays a greeting message to the user.
     */
    public void greetings() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Gutti");
        System.out.println("What can I do for you? Meow");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void goodBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon! Meow");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message based on the provided {@code GuttiException}.
     *
     * @param e The exception containing the error message.
     */
    public void generateError(GuttiException e) {
        System.out.println("____________________________________________________________");
        System.out.println(e.getMessage());
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The {@code TaskList} containing the tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        ArrayList<Task> task = tasks.getTasks();
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < task.size(); i++) {
            System.out.println((i + 1) + ". " + task.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads and returns a command from the user input.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}