package elara.command;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage);
}