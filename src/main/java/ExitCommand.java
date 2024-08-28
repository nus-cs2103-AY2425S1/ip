public class ExitCommand extends Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        exit();
    }

}
