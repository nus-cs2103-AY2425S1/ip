package beechat;

import java.io.*;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public boolean isLeave() {
        return false;
    }
}
