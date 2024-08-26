package GPT;
public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String input) {
        this.description = input.substring(4).trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (description.isEmpty()) {
            ui.showError("The description of a todo cannot be empty.\nUsage: todo [task description]\nExample: todo read a book");
            return;
        }
        Task newTask = new ToDo(description);
        taskList.addTask(newTask);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskAdded(newTask, taskList.getTasks().size());
    }
}
