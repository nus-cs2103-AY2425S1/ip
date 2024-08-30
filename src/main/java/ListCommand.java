public class ListCommand implements Command {
    private final String fullCommand;

    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showTasks(list);

    }
}
