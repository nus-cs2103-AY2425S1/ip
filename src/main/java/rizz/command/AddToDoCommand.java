package rizz.command;
import rizz.source.TaskList;
import rizz.source.Ui;
import rizz.source.Storage;
import java.io.IOException;
import rizz.task.ToDo;


public class AddToDoCommand extends Command {
    private String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (description == null || description.trim().isEmpty()) {
            ui.invalidToDoCommand();
        }
        try {
            ToDo newToDo = new ToDo(description, false);
            tasks.addTask(newToDo);
            ui.addToDo(newToDo, tasks.getLength());
            storage.saveTasks(tasks);
        } catch (IOException e) {
            ui.showError("Unexpected error: " + e.getMessage());
        }
    }
}
