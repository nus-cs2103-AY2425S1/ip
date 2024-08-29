public final class ExitCommand extends Command {
    public ExitCommand() {
        super("");
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.exit();
    }
}
