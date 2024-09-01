public abstract class Command {
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws GalliumException {

    }

    public boolean isExit() {
        return false;
    }
}
