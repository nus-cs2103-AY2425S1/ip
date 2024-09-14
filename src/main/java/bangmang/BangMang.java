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