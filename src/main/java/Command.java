public abstract class Command {
    public Command() {}
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws GarfieldException {
    }
}
