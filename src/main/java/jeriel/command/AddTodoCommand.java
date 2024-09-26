package jeriel.command;

import jeriel.task.Task;
import jeriel.util.TaskList;
import jeriel.task.Todo;
import jeriel.util.JerielException;
import jeriel.util.Storage;
import jeriel.util.Ui;

import java.io.IOException;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String arguments) {
        this.description = arguments.trim();
    }

    /**
     * Adds a todo to the task list and saves the task list to file.
     * @param tasks the task list to add the todo to
     * @param ui the ui to display the result (not used in GUI mode)
     * @param storage the storage to save to
     * @return String result for the GUI
     * @throws JerielException if the description is empty
     * @throws IOException if an error occurs while saving
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException {
        if (description.isEmpty()) {
            throw new JerielException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", task, tasks.size());
    }
}
