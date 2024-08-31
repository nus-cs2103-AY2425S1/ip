package command;

import utility.Ui;
import utility.TaskList;
import utility.Storage;

public class ByeCommand extends Command {

    @Override
    public Command parseArguments(String commandArguments) {
        return this;
    }

    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        Ui.say("Bye. Hope to see you again soon!\n");
        storage.saveTaskList(taskList);
        return taskList;
    }

}
