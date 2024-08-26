package tina;

import java.util.Scanner;

/**
 * The <code>Ui</code> class handles user interaction, including greeting the user, processing commands, and exiting the application.
 */
public class Ui {

    /**
     * Greets the user with a welcome message and a logo.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Tina");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Continuously reads user input, processes commands, and interacts with the task list until the user enter "bye".
     *
     * @param tasks The <code>TaskList</code> object that holds the list of tasks and handles task-related operations.
     */
    public void doTask(TaskList tasks) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                Parser.parseInput(input, tasks);
            } catch (TinaException e) {
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }
        sc.close();
    }
}
