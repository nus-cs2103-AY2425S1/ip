package nebula.command;

import nebula.storage.Storage;
import nebula.task.Task;
import nebula.task.TaskList;
import nebula.task.Todo;
import nebula.ui.Parser;
import nebula.ui.Ui;

public class AddTodoCommand extends Command {

    /**
     * Constructs an AddTodoCommand object with the specified description.
     *
     * @param description The command description containing the details of the Todo Task to be added.
     */
    public AddTodoCommand(String description) {
        super(description);
    }

    /**
     * Executes the add Todo command.
     * Parses the task description from the command, creates a new Todo Task,
     * and adds it to the task list.
     *
     * @param tasks   The task list to which the new Todo Task will be added.
     * @param ui      The UI component to display the result of adding the todo task.
     * @param storage The storage component (not used in this method).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String command = getDescription();

        String taskDescription = Parser.splitCommandAndTaskDescription(command);

        Task newTodo = new Todo(taskDescription);
        String addedTodo = tasks.addTask(newTodo);

        return addedTodo;
    }

    /**
     * Indicates whether this command will exit the application.
     *
     * @return false since adding a Todo Task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
