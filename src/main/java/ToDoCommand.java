public class ToDoCommand extends Command{

    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if(description.isEmpty()) {
            throw new NoDescription("todo", "todo <description>");
        }
        taskList.addTask(new ToDos(description));
        storage.saveTask(taskList.list);
        ui.addTaskMessage(taskList.list.getLast(), taskList.list.size());
    }
}
