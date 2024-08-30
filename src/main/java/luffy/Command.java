package luffy;

public abstract class Command {
    public abstract void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList);
}
