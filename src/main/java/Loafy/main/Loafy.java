package loafy.main;

import java.util.Scanner;

import loafy.command.Command;
import loafy.loafyexception.LoafyException;
import loafy.parser.Parser;
import loafy.storage.Storage;
import loafy.tasklist.TaskList;
import loafy.ui.Ui;

public class Loafy {
    private TaskList tasks;
    private final Ui ui;

    public Loafy(String filePath) {
        this.ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage, storage.getList());
        } catch (LoafyException exception) {
            ui.showStartError(exception);
            this.tasks = new TaskList(storage);
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.tasks, this.ui);
        } catch (LoafyException exception) {
            return ui.showError(exception);
        }
    }

    public String getGreeting() {
        return this.ui.showGreeting();
    }
}