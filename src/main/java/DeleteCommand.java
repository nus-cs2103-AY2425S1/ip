public class DeleteCommand extends Command {
    public DeleteCommand(String[] arguments) {
        super(false);
        super.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            tasklist.deleteTask(arguments);
        } catch (TrackieException e) {
            ui.displayErrorMessage(e);
        }
    }
}
