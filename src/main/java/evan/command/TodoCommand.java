package evan.command;

import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;
import evan.task.Todo;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        ui.showSuccess("Got it. I've added this todo:\n" + todo);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
