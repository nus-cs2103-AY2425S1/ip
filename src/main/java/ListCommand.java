public class ListCommand extends Command {
    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks);
    }
}