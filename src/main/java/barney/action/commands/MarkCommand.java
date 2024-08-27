package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.Task;
import barney.ui.Ui;

public class MarkCommand extends Command {
    HashMap<String, String> argumentMap;

    public MarkCommand(HashMap<String, String> argumentMap) {
        super("mark");
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String indexStr = argumentMap.get("index");

        if (!indexStr.matches("^\\d+$")) {
            throw new InvalidArgumentException("Please enter a task number!");
        }
        int index = Integer.parseInt(indexStr) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidArgumentException("Task number out of range!");
        }

        Task task = tasks.get(index);
        task.mark();

        ui.printMarked(task);

        return true;
    }
}