package bob;

import bob.commands.Command;
import bob.data.BobException;
import bob.data.TaskList;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Represents the main program.
 */
public class Bob {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;


    /**
     * Creates a new Bob instance.
     *
     * @param filePath The path to the file where the tasks are stored.
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (BobException e) {
            tasks = new TaskList();
        }
    }

    public Ui getUi() {
        return ui;
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (BobException e) {
            return e.getMessage();
        }
    }
}
