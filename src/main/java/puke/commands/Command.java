package puke.commands;

import puke.exceptions.PukeException;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, MessageBuilder messageBuilder) throws PukeException;
}
