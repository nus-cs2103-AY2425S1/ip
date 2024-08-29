package ai.command;

import ai.exception.AiException;
import ai.TaskList;
import ai.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws AiException;
    public abstract boolean isExit();
}
