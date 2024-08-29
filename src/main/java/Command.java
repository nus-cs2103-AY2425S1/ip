abstract class Command {
    abstract void execute(TaskList taskList, Ui ui);
    boolean isExit() {
        return false;
    }
}
