package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.TaskList;
import asura.data.tasks.Todo;
import asura.storage.Storage;
import asura.ui.Ui;

public class TodoCommand extends Command {
    String taskString;

    public TodoCommand(String taskString) {
        this.taskString = taskString;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) throws AsuraException {
        Todo newTodo = new Todo(taskString);
        tasklist.addTask(newTodo);
        storage.save(tasklist.getTaskList());
        output.append("Got it. I've added this todo:\n").append(newTodo).append("\n").append("Now you have ").append(tasklist.size()).append(" tasks in your list.\n");
        ui.printString(output.toString());
    }

    public boolean isExit() {
        return false;
    }
}
