package command;

import utility.TaskList;
import utility.Ui;
import utility.Storage;

public class ListCommand extends Command {

    @Override
    public Command parseArguments(String commandArguments) {
        return this;
    }

    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        Ui.say(taskList.toString());
        return taskList;
    }
}
