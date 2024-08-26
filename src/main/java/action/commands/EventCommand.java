package action.commands;

import java.util.HashMap;

import data.TaskList;
import data.exception.InvalidArgumentException;
import data.task.EventTask;
import ui.Ui;

public class EventCommand extends Command {
    HashMap<String, String> argumentMap;

    public EventCommand(HashMap<String, String> argumentMap) {
        super("event");
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String description = argumentMap.get("description");
        String at = argumentMap.get("from");
        String to = argumentMap.get("to");

        EventTask newEvent = new EventTask(description, at, to);
        tasks.add(newEvent);
        ui.printAddedTask(newEvent, tasks.size());

        return true;
    }
}
