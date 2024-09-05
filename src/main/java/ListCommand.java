public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        Ui.showTaskList(tasks);
    }
}
