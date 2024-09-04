package main;

import java.util.Scanner;

import exception.CommandFoundButInvalidException;
import exception.CommandNotFoundException;
import exception.EmptyStringException;

/**
 * The main class for the Hyperion application.
 * It initializes the user interface, storage, and task list, and processes user input commands.
 */
public class Hyperion {
    private Storage storage;
    private TaskList allTasks;
    private Ui ui;

    /**
     * Constructs a {@code Hyperion} instance with a specified file path for storage.
     * Initializes the user interface, loads tasks from storage, and starts processing user input.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Hyperion(String filePath) {
        try {
            this.ui = new Ui();
            this.ui.showWelcome();
            this.storage = new Storage(filePath);
            this.allTasks = new TaskList(storage.load());
            ui.display(this.allTasks.list());
        } catch (CommandFoundButInvalidException e) {
            System.out.print("There is an error" + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String input;

        boolean isOver = false;
        while (!isOver) {
            System.out.println("> ");
            input = scanner.nextLine();

            try {
                Parser parser = new Parser(input, allTasks, this.storage, this.ui);
                isOver = parser.isOver();
            } catch (EmptyStringException
                     | CommandFoundButInvalidException
                     | CommandNotFoundException e) {
                ui.display(e.getMessage());
            }
        }

        scanner.close();
    }

    /**
     * The entry point of the application.
     * Creates a new {@code Hyperion} instance with the specified file path for storage.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Hyperion("data/tasks.txt");
    }
}
