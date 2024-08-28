package alex;

import java.io.IOException;
import java.util.ArrayList;
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException, IOException;

    public abstract boolean isExit();
}
