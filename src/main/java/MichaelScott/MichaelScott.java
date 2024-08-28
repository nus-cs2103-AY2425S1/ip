package MichaelScott;

import MichaelScott.Storage.Storage;
import MichaelScott.Parser.Parser;
import MichaelScott.Task.TaskList;
import MichaelScott.Ui.Ui;
import MichaelScott.Command.Command;
import MichaelScott.Exception.MichaelScottException;

public class MichaelScott {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public MichaelScott(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList();
    }

    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command cmd = parser.parse(fullCommand);
                String response = cmd.execute(tasks);
                ui.showResponse(response);
                storage.saveTasks(tasks.getTasks());
                isRunning = !cmd.isExit();
            } catch (MichaelScottException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new MichaelScott("./userdata/Todo.txt").run();
    }
}