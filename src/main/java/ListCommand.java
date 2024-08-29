public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
