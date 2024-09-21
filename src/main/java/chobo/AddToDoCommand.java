package chobo;

/**
 * The type Add to do command.
 */
public class AddToDoCommand extends Command {
    private String taskName;

    /**
     * Instantiates a new Add to do command.
     *
     * @param taskName the task description
     */
    public AddToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InputException {
        Task todo = new ToDo(taskName, false);
        taskList.addTask(todo);
        storage.saveTasks(taskList.getTasks());
        return ui.showTaskAdded(todo, taskList.getTotalTask());
    }
}
