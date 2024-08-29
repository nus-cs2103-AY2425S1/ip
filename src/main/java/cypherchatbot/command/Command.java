package cypherchatbot.command;

import cypherchatbot.CypherException;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

public abstract class Command {

    public abstract void execute (TaskList task, Ui ui, Storage storage) throws CypherException;
    public abstract boolean isExit();
}
