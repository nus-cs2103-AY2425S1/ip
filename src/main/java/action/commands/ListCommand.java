package action.commands;

import java.util.HashMap;

import data.TaskList;
import data.exception.InvalidArgumentException;
import ui.Ui;

public class ListCommand extends Command {
    HashMap<String, String> argumentMap;

    public ListCommand(HashMap<String, String> argumentMap) {
        super("list");
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        ui.printList(tasks);
        return true;
    }
}
