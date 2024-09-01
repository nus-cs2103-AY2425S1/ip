public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showOutput("Here are the tasks in your list:" + taskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
