package friendlybot;

import java.util.ArrayList;

import friendlybot.task.TaskList;

/**
 * A stub for FriendlyBot. Used for testing purposes.
 */
public class FriendlyBotStub {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor for FriendlyBotStub, that does not require any file paths as arguments.
     */
    public FriendlyBotStub() {
        this.storage = new Storage("", "");
        this.tasks = new TaskList(new ArrayList<>());
        this.ui = new Ui();
    }

    public Storage getStorage() {
        return this.storage;
    }

    public TaskList getTasks() {
        return this.tasks;
    }
    public Ui getUi() {
        return this.ui;
    }
}
