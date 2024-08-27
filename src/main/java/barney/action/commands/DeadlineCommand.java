package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.DeadlineTask;
import barney.ui.Ui;

public class DeadlineCommand extends Command {
    HashMap<String, String> argumentMap;

    public DeadlineCommand(HashMap<String, String> argumentMap) {
        super("deadline");
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String description = argumentMap.get("description");
        String by = argumentMap.get("by");

        DeadlineTask newDeadline = new DeadlineTask(description, by);
        tasks.add(newDeadline);
        ui.printAddedTask(newDeadline, tasks.size());

        return true;
    }
}
