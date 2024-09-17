package main;

import java.nio.file.Path;
import java.nio.file.Paths;

import main.command.Command;
import main.exceptions.PrinceException;
import main.tasks.TaskList;
import main.util.Parser;
import main.util.Storage;
import main.util.Ui;

/**
 * Prince is a chatbot that interacts with users.
 */
public class Prince {
    private static final Path FILE_PATH = Paths.get("data", "storage.txt");

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor for Prince.
     */
    public Prince() {
        ui = new Ui();
        assert ui != null : "UI should not be null after initialisation";
        storage = new Storage(FILE_PATH);
        assert storage != null : "Storage should not be null after initialisation";
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (PrinceException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Return a string containing Prince's response to the user's input
     * @param input Input by the user.
     * @return Prince's response to the user.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        if (isExit) {
            return "Prince going to sleep...";
        }
        try {
            ui.resetBuilder();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            assert c != null : "Command should not be null after parsing";
            isExit = c.isExit();
            return ui.getBuilder();
        } catch (PrinceException e) {
            return e.toString();
        }
    }
}
