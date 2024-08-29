package jeriel.command;
import jeriel.task.*;
import jeriel.util.*;
import java.io.IOException;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String arguments) {
        this.description = arguments.trim();
    }

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
