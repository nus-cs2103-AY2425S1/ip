package streams.command;

import streams.util.Storage;
import streams.exception.StreamsException;
import streams.task.TaskList;
import streams.util.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws StreamsException;
    public boolean isExit() {
        return false;
    }
}