package peppa;

import peppa.commands.Command;
import peppa.data.PeppaException;
import peppa.data.TaskList;
import peppa.parser.Parser;
import peppa.storage.Storage;
import peppa.ui.Ui;

/**
 * Represents the main program.
 */
public class Peppa {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;


    /**
     * Creates a new Peppa instance.
     *
     * @param filePath The path to the file where the tasks are stored.
     */
    public Peppa(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (PeppaException e) {
            tasks = new TaskList();
        }
    }

    public Ui getUi() {
        return ui;
    }

    public TaskList getTaskList() {
        return tasks;
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (PeppaException e) {
            return e.getMessage();
        }
    }
}
