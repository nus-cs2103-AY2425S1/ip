public class ListCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = tasks.showList();
        ui.showMessage(listOfTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
