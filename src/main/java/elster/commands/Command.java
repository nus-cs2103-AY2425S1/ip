package elster.commands;

import elster.Storage;
import elster.TaskList;
import elster.Ui;

public abstract class Command {
    protected Ui ui;
    protected Storage storage;
    protected String input;
    protected TaskList tasklist;
    Command(Ui ui, TaskList tasklist, Storage storage, String input) {
        this.ui = ui;
        this.tasklist = tasklist;
        this.storage = storage;
        this.input = input;
    }
    public abstract String execute();
}
