package bob;

public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage);

}