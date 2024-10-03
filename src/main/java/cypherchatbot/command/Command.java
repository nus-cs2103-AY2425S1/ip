package cypherchatbot.command;

import cypherchatbot.CypherException;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

/**
 * The Command class is an abstract class that represents a user command
 * in the Cypher Chat Bot Application. Each command has two methods, one is execute which will carry
 * out the Command and the isExit method which inform the main Event loop weather the application is
 * ending.
 */
public abstract class Command {

    public abstract String execute(TaskList task, Ui ui, Storage storage) throws CypherException;
    public abstract boolean showExitStatus();
}
