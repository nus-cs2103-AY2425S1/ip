package action.commands;

import java.util.HashMap;

import action.CommandManager;
import data.TaskList;
import data.exception.InvalidArgumentException;
import data.exception.MissingFlagException;
import ui.Ui;

public abstract class Command {
    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException;

    void verifyFlags(HashMap<String, String> argumentMap) throws InvalidArgumentException {
        for (String arg : CommandManager.CommandType.fromString(this.name).commandArgs) {
            if (!argumentMap.containsKey(arg)) {
                throw new MissingFlagException("Missing" + arg + " for " + name + "!");
            }
            if (argumentMap.get(arg).isEmpty()) {
                throw new InvalidArgumentException("The " + arg + " of a " + name + " cannot be empty!");
            }
        }
    }
}
