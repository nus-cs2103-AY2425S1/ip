package myapp.core;

import myapp.command.Command;
import myapp.task.TaskList;

/**
 * Represents the BingBongBot that handles user interactions, manages tasks, and
 * processes commands.
 */
public class BingBongBot {
    private final BingBongUI ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a BingBongBot with the specified UI and storage.
     *
     * @param ui the UI to interact with the user.
     * @param storage the storage system to load and save tasks.
     */
    public BingBongBot(BingBongUI ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        try {
            this.tasks = new TaskList(storage.load());
        } catch (BingBongException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the BingBongBot, allowing it to run and process user commands until
     * the user issues an exit command.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BingBongException e) {
                ui.showResponse(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showResponse("Invalid task number. Please enter a valid number.");
            } catch (IndexOutOfBoundsException e) {
                ui.showResponse("Task number is out of range. Please enter a valid task number.");
            } catch (Exception e) {
                ui.showResponse("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
