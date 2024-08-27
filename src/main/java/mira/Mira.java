package mira;

import mira.command.Command;
import mira.command.ExitCommand;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Mira is a simple chatbot that echoes user commands and exits when the user types "bye".
 * It interacts with the user via the Ui class, which handles input and output operations.
 */
public class Mira {
    private final Ui ui; // handle user interface
    private final Storage storage; // storage for saving, loading tasks
    private TaskList tasks; // Manages tasks

    public Mira(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (MiraException e) {
            ui.showMessage(e.getMessage());
            this.tasks = new TaskList();
        } catch (SecurityException e) {
            ui.showMessage("Please make sure the directory has read and write permissions.");
            this.tasks = new TaskList();
        } catch (IOException e) {
            ui.showMessage("File path for storing of tasks is unusable.");
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot, continuously reading user commands and responding until the user types "bye".
     * The chatbot echoes all commands and displays a goodbye message when exiting.
     */
    public void run() {
        // show welcome message with multiline text
        ui.showMessage("Hello! I'm Mira\nWhat can I do for you?");
        Command command = null;
        do {
            try {
                // read user input until a newline is entered
                String userInput = ui.readCommand();
                command = Parser.parse(userInput);
                command.setTaskList(tasks);
                String commandResult = command.execute();
                ui.showMessage(commandResult);
                if (command instanceof Savable) { // if command is Savable
                    ((Savable) command).save(storage);
                }
            } catch (MiraException e) {
                ui.showMessage(e.getMessage());
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.showMessage("Please provide a valid task number.");
            } catch (DateTimeParseException e) {
                ui.showMessage("Please input a valid date: 'd/M/yyyy HHmm'.");
            } catch (IOException e) {
                ui.showMessage("File path for storing of tasks is unusable.");
            } catch (Exception e) {
                ui.showMessage("Error: " + e.getMessage());
            }
        } while (!ExitCommand.isExit(command));
    }

    /**
     * The main method to start the Mira chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Mira("./data/mira.txt").run();
    }
}
