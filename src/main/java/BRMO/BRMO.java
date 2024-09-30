package BRMO;

import java.io.IOException;
import java.text.ParseException;

import BRMO.task.TaskList;

/**
 * The main class for the BRMO task management application.
 * This class initializes the user interface, loads tasks from storage,
 * processes user commands, and saves tasks to storage upon exiting.
 */
public class BRMO {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * The main handler of the chatbot behaviour.
     *
     * @param args command line arguments (not used)
     * @throws InvalidCommandException if an invalid command is entered
     * @throws ParseException if a task's date is in an invalid format
     */
    public BRMO() throws InvalidCommandException, ParseException {

        ui = new Ui();
        storage = new Storage("./data/BRMO.txt");
        taskList = new TaskList();

        // Load tasks from storage, handle possible exceptions
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("An error occurred while reading the data file: " + e.getMessage());
        } catch (ParseException e) {
            ui.showError("An error occurred while parsing the date: " + e.getMessage());
        } catch (InvalidCommandException e) {
            ui.showError("Invalid command: " + e.getMessage());
        }
    }

    public String getResponse(String input) throws ParseException {
        if (input.equals("bye")) {
            // Save tasks to storage, handle possible exceptions
            try {
                storage.save(taskList.getTasks());
            } catch (IOException e) {
                return ui.showError("An error occurred while saving the data file: " + e.getMessage());
            }

            return "We shall meet again.";
        }

        try {
            return Parser.parse(input, taskList, ui);
        } catch (InvalidCommandException e) {
            return ui.showError(e.getMessage());
        }
    }

    public String getWelcomeMessage() {
        return ui.showWelcome();
    }
}