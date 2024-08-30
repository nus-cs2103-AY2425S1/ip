package atreides.command;

import atreides.task.TaskList;
import atreides.ui.AtreidesException;
import atreides.ui.Storage;
import atreides.ui.Ui;

public interface Command {

    void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException;

     boolean isExit();
}
