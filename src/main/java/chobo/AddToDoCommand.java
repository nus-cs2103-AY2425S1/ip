package chobo;

public class AddToDoCommand extends Command {
    private String taskDescription;

    public AddToDoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InputException {
        Task todo = new ToDo(taskDescription, false);
        taskList.addTask(todo);
        storage.saveTasks(taskList.getTasks());
        return ui.showTaskAdded(todo, taskList.getTotalTask());
    }
}
