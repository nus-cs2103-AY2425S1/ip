package friendlybot;

import friendlybot.task.TaskList;

import java.util.ArrayList;

public class FriendlyBotStub {
    public Storage storage;
    public TaskList tasks;
    public Ui ui;

    public FriendlyBotStub() {
        this.storage = new Storage("", "");
        this.tasks = new TaskList(new ArrayList<>());
        this.ui = new Ui();
    }
}
