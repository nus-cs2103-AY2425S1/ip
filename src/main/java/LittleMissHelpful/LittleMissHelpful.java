package LittleMissHelpful;

import LittleMissHelpful.Command.Command;
import LittleMissHelpful.Exception.InvalidCommandException;
import LittleMissHelpful.Parser.Parser;
import LittleMissHelpful.Storage.Storage;
import LittleMissHelpful.Tasks.TaskList;
import LittleMissHelpful.Ui.Ui;

public class LittleMissHelpful {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public LittleMissHelpful(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (InvalidCommandException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }

        // Assert that tasks should never be null
        assert tasks != null : "Task list should not be null after initialization";
    }

    public String getGreeting() {
        try {
            return ui.showWelcome();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getResponse(String input) {
        // Assert that input should not be null or empty
        assert input != null && !input.isEmpty() : "Input should not be null or empty";

        try {
            Command command = Parser.parse(input);
            // Assert that command should not be null
            assert command != null : "Command should not be null after parsing";

            String response = command.execute(tasks, ui, storage);
            return response;
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }
}
