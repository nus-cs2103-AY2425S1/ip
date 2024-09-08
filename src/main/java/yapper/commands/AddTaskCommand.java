package yapper.commands;

import yapper.components.Task;

/**
 * Parent class of task-adding command classes.
 */
public abstract class AddTaskCommand extends Command {
    public AddTaskCommand() {
        super();
    }
    public abstract Task createTaskToAdd(String... arguments);
}
