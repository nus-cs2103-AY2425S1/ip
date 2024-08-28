public class ListCommand extends Command {

    private String fullCommand;

    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
