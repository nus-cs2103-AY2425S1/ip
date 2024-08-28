public class ExitCommand extends Command {
    public ExitCommand(TaskList tasks) {
        super(tasks);
        super.exit();
    }
    @Override
    public void execute() {
        Ui.printByeMessage();
    }
}
