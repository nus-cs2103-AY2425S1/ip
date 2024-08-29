public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskException {
        ui.PixelSays("Bye. Hope to see you again soon!");
        ui.closeUi();
    }
}
