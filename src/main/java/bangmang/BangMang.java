package bangmang;

import bangmang.command.Command;
import bangmang.exception.InvalidCommandException;
import bangmang.parser.Parser;
import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;

public class BangMang {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public BangMang(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (InvalidCommandException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }

        // Assert that tasks should not be null
        assert tasks != null : "List of tasks should not be null";
    }

    public String getGreeting() {
        /**
         * Returns string greeting
         */
        try {
            return ui.showWelcome();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getResponse(String input) {
        /**
         * Returns response given input command
         *
         * @param input refers to user-input in chatbot
         */

        // Assert that input should not be null or empty
        assert input != null && !input.isEmpty() : "Input should not be null or empty";

        try {
            Command command = Parser.parse(input);

            // Assert that command should not be null
            assert command != null : "Command should not be null";

            String response = command.execute(tasks, ui, storage);
            return response;
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }
}