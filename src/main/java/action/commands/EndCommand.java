package action.commands;

import java.util.HashMap;

import data.TaskList;
import data.exception.InvalidArgumentException;
import ui.Ui;

public class EndCommand extends Command {
    HashMap<String, String> argumentMap;

    public EndCommand(HashMap<String, String> argumentMap) {
        super("bye");
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);
        return false;
    }

}
