package controllers.commands;

import models.TaskList;

public interface Command {
    void execute(TaskList taskList);
}
