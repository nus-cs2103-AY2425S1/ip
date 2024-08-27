package strand.Commands;

import strand.Exceptions.StrandException;

public abstract class Command {
    public abstract void execute(strand.TaskList tasks, strand.Ui ui, strand.Storage storage) throws StrandException;

    public Boolean isRunning() {
        return true;
    }
}
