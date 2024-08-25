package simon;

public class ListCommand implements Command{
    int index;
    public ListCommand() {
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
