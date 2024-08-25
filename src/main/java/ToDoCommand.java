public class ToDoCommand implements Command{
    String name;

    public ToDoCommand(String name) {
        this.name = name;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ToDo task = new ToDo(name, taskList.size());
        taskList.add(task);
        ui.showTaskAdded(task, taskList.size());
        storage.saveToFile(taskList.toArr());

    }
}

