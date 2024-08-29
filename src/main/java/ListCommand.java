public class ListCommand extends Command {
    public ListCommand(String commandBody) {
        super(commandBody);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}