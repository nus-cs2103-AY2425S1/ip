package luffy;

/**
 * Represents a command that depends on user input
 */
public abstract class Command {
    public abstract void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList);
}
