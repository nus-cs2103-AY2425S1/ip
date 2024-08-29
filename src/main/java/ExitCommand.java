public class ExitCommand extends Command {
    public ExitCommand() {
        super(CommandType.BYE);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFarewell();
    }
}