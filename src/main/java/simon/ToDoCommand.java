package simon;
/**
 * Represents a command to create and add a new to-do task to the task list.
 * Implements the {@link Command} interface and handles the execution of the to-do command.
 */
public class ToDoCommand implements Command{
    String name;
    /**
     * Constructs a ToDoCommand with the specified task name.
     *
     * @param name the name or description of the to-do task
     */
    public ToDoCommand(String name) {
        this.name = name;
    }
    /**
     * Executes the to-do command by creating a new {@link ToDo} task, adding it to the task list,
     * updating the user interface to show the newly added task, and saving the updated task list to the file.
     *
     * @param taskList the list of tasks to which the new task will be added
     * @param ui the user interface used to display information to the user
     * @param storage the storage object used to save the updated task list to the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ToDo task = new ToDo(name, taskList.size());
        taskList.add(task);
        ui.showTaskAdded(task, taskList.size());
        storage.saveToFile(taskList.toArr());

    }
}

