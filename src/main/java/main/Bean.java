package main;

import command.Command;
import exception.DukeException;

/**
 * This class is used to control the main logical flow of the Bean program
 */
public class Bean {
    private TaskList taskList;

    private Storage storage;
    private Ui ui;

    private String commandType;

    private String path = "./data/tasks.txt";

    /**
     * Creates the program with the given storage path
     */
    public Bean() {
        storage = new Storage(path);
        taskList = new TaskList(storage.readStorage());
        ui = new Ui();
    }

    /**
     * Runs the logic of the program
     */

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.execute(taskList, ui, storage);
            commandType = c.getClass().getSimpleName();
            return output;
        } catch (DukeException e) {
            return "Error: " + e.getMessage();
        }
    }
    public String getCommandType() {
        return commandType;
    }

}
