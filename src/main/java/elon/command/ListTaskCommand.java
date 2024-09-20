package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.TaskList;

import java.io.IOException;

public class ListTaskCommand extends Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        return ui.listTasks(list);
    }
}
