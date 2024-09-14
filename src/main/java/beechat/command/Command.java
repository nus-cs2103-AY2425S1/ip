package beechat.command;

import beechat.util.Storage;
import beechat.task.TaskList;
import beechat.util.Ui;

import java.io.*;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public boolean isLeave() {
        return false;
    }
}
