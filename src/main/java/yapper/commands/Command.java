package yapper.commands;

import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.TaskList;
import yapper.exceptions.YapperException;

/**
 * Command class and parent class of all concrete command classes.
 */
public abstract class Command {
    public Command() {
    }
    public abstract String execute(Parser parser, TaskList taskList, Storage storage) throws YapperException;
    public abstract String commandDescription();
}
