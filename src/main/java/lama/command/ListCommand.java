package lama.command;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;

public class ListCommand extends Command {
    @Override
    public void run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        ui.showListCommand(taskList);
    }

}
