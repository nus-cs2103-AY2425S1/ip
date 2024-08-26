public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String input) {
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task removedTask = taskList.getTasks().get(index);
            taskList.deleteTask(index);
            storage.saveTasks(taskList.getTasks());
            ui.showTaskRemoved(removedTask, taskList.getTasks().size());
        } catch (GPTException e) {
            ui.showError(e.getMessage());
        }
    }
}
