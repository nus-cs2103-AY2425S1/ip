package reo;

import reo.storage.TaskStorage;
import reo.tasks.TaskList;
import reo.ui.Ui;

public class Reo {
    private TaskList tasklist;
    private Ui ui;
    private TaskStorage storage;

    public Reo() {
        TaskList taskList;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Reo heard: " + input;
    }
}
