package revir.user.ui.tui;

import java.io.IOException;
import java.util.Scanner;

import revir.tasks.TaskList;
import revir.user.Parser;
import revir.user.command.Command;
import revir.user.ui.Ui;

/**
 * The Ui class represents the user interface of the application.
 * It provides methods for displaying messages, reading user input, and handling
 * errors.
 */
public class Tui implements Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui object with the given name.
     *
     * @param name the name of the user interface
     */
    public Tui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message.
     */
    @Override
    public void run(TaskList taskList) {
        Parser parser = new Parser();

        System.out.println("Hello! I'm Revir\nWhat can I do for you?");
        this.showLine();
        boolean exit = false;
        while (!exit) {
            String input = this.readInput();
            try {
                Command c = parser.parse(input);
                c.execute(this, taskList);
                exit = c.isExit();
            } catch (NumberFormatException e) {
                this.showError("Invalid task index. Expected a number.");
            } catch (IOException e) {
                this.showError("Unable to save file: " + e.getMessage());
            } catch (Exception e) {
                this.showError(e.getMessage());
            }
        }
    }

    /**
     * Displays a line separator.
     */
    private void showLine() {
        System.out.println("----------------------------------------");
    }

    /**
     * Displays an error message.
     *
     * @param error the error message to display
     */
    public void showError(String error) {
        this.showLine();
        System.err.println(error);
        this.showLine();
    }

    /**
     * Displays an exit message.
     */
    public void showExit() {
        this.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.scanner.close();
    }

    /**
     * Displays the result of an operation.
     *
     * @param result the result to display
     */
    public void showResult(String result) {
        this.showLine();
        System.out.println(result);
        this.showLine();
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return the next line of input
     */
    public String readInput() {
        String nextLine = this.scanner.nextLine();
        return nextLine;
    }

    /**
     * Checks if the scanner is open.
     *
     * @return true if the scanner is open, false otherwise
     */
    public boolean isOpen() {
        return this.scanner.hasNext();
    }
}
