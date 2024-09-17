package ScoobyDoo.Command;

import ScoobyDoo.UI.UI;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.Task;
import ScoobyDoo.task.TaskList;
import ScoobyDoo.task.Todo;

public class TodoCommand extends Command{
    private final String description;
    public TodoCommand (String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        Task newTask  = new Todo(description);
        String addTaskMsg = taskList.addTask(newTask);
        storage.updateFile(newTask.toFileFormatString());
        return ui.response(addTaskMsg);
    }
}