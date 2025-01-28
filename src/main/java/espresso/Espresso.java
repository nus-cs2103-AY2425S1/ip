//Solution below inspired by https://github.com/nus-cs2103-AY2425S1/ip/pull/557 with permission
package espresso;

import espresso.task.TaskList;
import espresso.storage.Storage;
import espresso.parser.Parser;
import espresso.command.InvalidCommandException;
import espresso.ui.Ui;

import java.io.IOException;
import java.text.ParseException;

/**
 * Represents the main class for the Espresso chatbot.
 * This class initializes the user interface, storage, and task list, and manages
 * the application's command input and output.
 */
public class Espresso {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * ,
     * The main handler of the chatbot.
     *
     * @throws InvalidCommandException if an invalid command is entered
     * @throws ParseException          if a task's date is in an invalid format
     */
    public Espresso() throws InvalidCommandException, ParseException {
        ui = new Ui();
        storage = new Storage("./data/Espresso.txt");
        taskList = new TaskList();
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printError("An error occurred while reading data from file: " + e.getMessage());
        } catch (ParseException e) {
            ui.printError("An error occurred while parsing the date file: " + e.getMessage());
        } catch (InvalidCommandException e) {
            ui.printError("Invalid command: " + e.getMessage());
        }
    }

    public String getResponse(String input) throws ParseException {
        if (input.equals("bye")) {
            try {
                storage.save(taskList.getTasks());
            } catch (IOException e) {
                return ui.printError("An error occurred while saving the data file: " + e.getMessage());
            }
            return "It was nice talking to you!" + "\n" + "Until next time....";
        }
        try {
            return Parser.parse(input, taskList, ui);
        } catch (InvalidCommandException e) {
            return ui.printError(e.getMessage());
        }
    }
}