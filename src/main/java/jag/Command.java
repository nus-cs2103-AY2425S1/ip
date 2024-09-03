package jag;

/**
 * Command abstract class that is responsible for executing any command
 * which can be found in the list of commands in the enum "Commands"
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract Boolean isExit();
}
