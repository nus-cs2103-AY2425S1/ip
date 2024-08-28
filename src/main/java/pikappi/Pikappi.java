package pikappi;

import pikappi.command.Command;
import pikappi.exception.PikappiException;

public class Pikappi {
    protected static Ui ui = new Ui();
    protected static Storage storage = new Storage("data/pikappi.txt");
    protected static TaskList tasks = new TaskList();
    protected static Parser parser;

    public static void main(String[] args) throws PikappiException {
        tasks = storage.load();
        ui.greet();
        boolean isExit = false;
        parser = new Parser(storage, tasks, ui);

        while (!isExit) {
            String command = ui.readCommand();
            ui.showLine();
            try {
                Command c = parser.parse(command);
                c.execute(tasks, ui, storage);
            } catch (PikappiException e) {
                ui.showErrorMessage(e.getMessage());
            }
            isExit = parser.isExit();
            ui.showLine();
        }
    }
}
