package hoshi.command;

import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * Command interface where respective children command classes will implement from to handle various logic
 */
public interface Command {
    String execute(TaskList tasks, Ui ui, Storage storage);
}
