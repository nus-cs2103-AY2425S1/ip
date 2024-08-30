package main.froggy;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show("Task List:");
        taskList.printTasks();
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
