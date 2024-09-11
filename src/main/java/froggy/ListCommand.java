package froggy;

/**
 * Prints whole task list out.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show("Task List:");
        taskList.printTasks();
        ui.showLine();
    }

    @Override
    public String executeAndGetOutput(TaskList taskList, Ui ui, Storage storage) {
        return "Task List:\n" + taskList.getTasksToString() + ui.getLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
