package mummy.command;

import java.util.HashMap;

import mummy.task.TaskList;
import mummy.task.TaskListException;
import mummy.ui.MummyException;
import mummy.ui.Ui;
import mummy.utility.Parser;
import mummy.utility.Storage;

public class MarkCommand extends Command {

    public MarkCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MummyException {
        HashMap<String, String> arguments = this.getArguments();

        String taskIndexString = arguments.get("description");

        if (taskIndexString == null) {
            throw new MummyException("Task number is required");
        }

        int taskIndex = Parser.parseIntOrDefault(
                arguments.getOrDefault("description", ""),
                -1);

        try {
            taskList.markTask(taskIndex);
            saveTaskListToStorage(taskList, storage);
            ui.show("Nice! I've marked this task as done:\n\t" + taskList.get(taskIndex));
        } catch (TaskListException exception) {
            throw new MummyException("Something went wrong when marking task as done: "
                    + exception.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
