package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

public abstract class Command {
    private String input;

    public Command(String input) {
        this.input = input;
    }

    public String getInput() {
        return this.input;
    }

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException;
}
