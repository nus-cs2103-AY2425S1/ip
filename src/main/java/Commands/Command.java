package Commands;

import Main.Storage;
import Tasks.TaskList;
import Main.Ui;

public interface Command {
    String execute(TaskList tasks, Ui ui, Storage storage);
}