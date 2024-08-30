package luffy;

public class ListCommand extends Command {

    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {
        ui.showLine();
        ui.displayTasks(taskList);
        ui.showLine();
    }
}