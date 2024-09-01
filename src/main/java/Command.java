import exceptions.AstorException;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws AstorException, IOException;
    public abstract boolean isExit();
}
