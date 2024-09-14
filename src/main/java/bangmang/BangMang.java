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
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);
            return response;
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }
}