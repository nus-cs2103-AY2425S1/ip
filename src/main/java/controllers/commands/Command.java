package controllers.commands;

import controllers.OutputHandler;
import models.TaskList;

public interface Command {
    void execute(TaskList taskList, OutputHandler outputHandler);
}
