abstract class Command {
    Command() {}

    abstract void execute(TaskList taskList, Ui ui, Storage storage);

    boolean isExit() {
        return false;
    }
}
