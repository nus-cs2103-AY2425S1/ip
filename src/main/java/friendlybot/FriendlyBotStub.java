package friendlybot;

import friendlybot.task.TaskList;

import java.util.ArrayList;

/**
 * A stub for FriendlyBot. Used for testing purposes.
 */
public class FriendlyBotStub {
    public Storage storage;
    public TaskList tasks;
    public Ui ui;

    /**
     * A constructor for FriendlyBotStub, that does not require any file paths as arguments.
     */
    public FriendlyBotStub() {
        this.storage = new Storage("", "");
        this.tasks = new TaskList(new ArrayList<>());
        this.ui = new Ui();
    }
}
