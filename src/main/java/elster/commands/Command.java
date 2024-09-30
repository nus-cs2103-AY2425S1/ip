package elster.commands;

import elster.Storage;
import elster.TaskList;
import elster.Ui;

/**
 * Parent class that determines base behaviour of all commands, primarily that of having a constructor
 * that takes in an ui, storage, input, task list as well as the method execute for when the command is executed.
 * Note that the execute command returns a string.
 */
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
