package mummy.command;

import java.util.HashMap;

import mummy.task.Task;
import mummy.task.TaskList;
import mummy.task.TaskListException;
import mummy.ui.MummyException;
import mummy.ui.Ui;
import mummy.utility.Parser;
import mummy.utility.Storage;


public class DeleteCommand extends Command {

    public DeleteCommand(HashMap<String, String> arguments) {
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
            Task task = taskList.remove(taskIndex);
            saveTaskListToStorage(taskList, storage);
            ui.show(String.format(
                    "Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.\n",
                    task, taskList.getCount()
            ));
        } catch (TaskListException exception) {
            throw new MummyException("Something went wrong when deleting task: "
                    + exception.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
