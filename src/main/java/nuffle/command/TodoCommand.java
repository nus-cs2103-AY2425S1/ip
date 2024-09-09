package nuffle.command;

import nuffle.exception.NuffleException;
import nuffle.storage.Storage;
import nuffle.task.Task;
import nuffle.task.TaskList;
import nuffle.task.Todo;
import nuffle.ui.Ui;

public class TodoCommand extends Command {
    private final String userInput;

    public TodoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws NuffleException {
        assert userInput != null && !userInput.trim().isEmpty() : "Command should not be null or empty";
        // Program will add a To-do task to the list

        String desc = userInput.substring(4);
        if (desc.trim().isEmpty()) {
            throw NuffleException.noDesc("todo");
        }
        Task task = new Todo(desc.trim());
        assert task != null : "Task should not be null before adding to the list";
        tasks.addTask(task);
        return ui.addTaskMessage(task, tasks.getSize());
    }

    public boolean isTerminateProgram() {
        return false;
    }
}
