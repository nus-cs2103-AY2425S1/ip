package derek.command;

import derek.Storage;
import derek.Ui;
import derek.task.Task;
import derek.task.TaskList;


/**
 * The {@code TodoCommand} class adds a todo task to the task list.
 * It extends the {@code TaskCommand} class and executes the command to add the todo task.
 */
public class TodoCommand extends TaskCommand {

    private Storage storage;
    private Ui ui;
    /**
     * Constructs a {@code TodoCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public TodoCommand(String command, Storage storage, Ui ui) {
        super(command);
        this.storage = storage;
        this.ui = ui;
    }

    /**
     TodoCommand todoCommand = new TodoCommand(this.command);
     String name = todoCommand.getTask();
     Task task = Task.toDoTask(name);
     return todoCommand.execute(task, this.storage, this.ui);
     */
    public String execute() {
        String name = this.getTask();
        Task task = Task.toDoTask(name);
        TaskList taskList = storage.getTaskList();
        taskList.add(task);
        return ui.addTask(task);

    }

}
