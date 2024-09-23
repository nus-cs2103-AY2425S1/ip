package trackie.ui;

import javafx.scene.image.Image;
import trackie.commands.Command;
import trackie.parsing.Parser;
import trackie.storage.Storage;
import trackie.storage.TaskList;

public class Trackie {
    private Storage storage;
    private TaskList tasks;

    public Trackie(String filepath) {
        tasks = new TaskList();
        storage = new Storage(filepath, tasks);
        storage.load();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(this.tasks, this.storage);
        } catch (TrackieException e) {
            return e.getMessage();
        }
    }
}
