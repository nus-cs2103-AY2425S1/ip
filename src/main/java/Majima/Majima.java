package Majima;

import Majima.command.Command;
import Majima.storage.Storage;
import Majima.task.TaskList;
import Majima.ui.Ui;

public class Majima {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Majima object and the datapath for the .txt file.
     */
    public Majima(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MajimaException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Processes user input and returns Majimabot's response.
     *
     * @param input User's input string
     * @return String Response from Majimabot
     */
    public String getResponse(String input) {
        ui.clearOutput();
        try {
            Command parsedCommand = Parser.parse(input);
            ui.clearOutput(); // Clear previous outputs
            parsedCommand.execute(tasks, ui, storage); // Execute the command
        } catch (MajimaException e) {
            ui.showError(e.getMessage());
        }
        return ui.getOutput(); // Return the output collected in Ui
    }

    public Ui getUi() {
        return ui;
    }
}

