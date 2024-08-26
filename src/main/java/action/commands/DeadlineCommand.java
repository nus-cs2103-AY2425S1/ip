package action.commands;

import java.util.HashMap;

import data.TaskList;
import data.exception.InvalidArgumentException;
import data.task.DeadlineTask;
import ui.Ui;

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
