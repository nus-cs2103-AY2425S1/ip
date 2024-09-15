package nebula.command;

import nebula.ui.Parser;
import nebula.storage.Storage;
import nebula.task.TaskList;
import nebula.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String command = getDescription();

        int taskNum = Parser.splitCommandAndTaskNumber(command);
        System.out.println(tasks.unmarkTask(taskNum));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
