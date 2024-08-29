package shrimp.command;

import shrimp.task.TaskList;
import shrimp.utility.Ui;

public interface Command {
    void run(TaskList tasks, Ui ui);
}
