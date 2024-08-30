package jeriel.command;
import jeriel.task.*;
import jeriel.util.*;
import java.io.IOException;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String arguments) {
        this.description = arguments.trim();
    }

    /**
     * Adds a todo to the task list, and saves the task list to file.
     *
     * @param tasks the task list to add the todo to
     * @param ui the ui to display the result
     * @param storage the storage to save to
     * @throws JerielException if the description is empty
     * @throws IOException if there is an error saving the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException {
        if (description.isEmpty()) {
            throw new JerielException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
