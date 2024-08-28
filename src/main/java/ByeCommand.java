public class ByeCommand extends Commands {

    public ByeCommand(String[] split) {
        super(split);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        System.exit(0);
    }
}
