package qwerty.command;

import java.util.HashMap;

import qwerty.QwertyException;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.Ui;

public abstract class Command {
    private HashMap<String, String> args;

    public Command(HashMap<String, String> args) {
        this.args = args;
    }

    public HashMap<String, String> getArgs() {
        return this.args;
    }

    public abstract boolean isExitCommand();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws QwertyException;
}
