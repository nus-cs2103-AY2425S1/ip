public class AddToDoCommand extends Command {
    private String description;

    public AddToDoCommand(String input) throws LunaBotException {
        this.description = input.substring(5);
        if (description.isEmpty()) {
            throw new LunaBotException("Description of task cannot be empty. Please fill in the required fields.");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LunaBotException {
        ToDo todo = new ToDo(description);
        taskList.addTask(todo);
        storage.save(taskList.getTasks());
        ui.printTaskAdded(todo, taskList.size());
    }
}
