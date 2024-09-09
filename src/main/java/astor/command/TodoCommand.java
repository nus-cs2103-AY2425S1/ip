package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.AstorException;
import astor.exception.EmptyTaskInfoException;
import astor.task.Task;
import astor.task.Todo;

import java.io.IOException;

/**
 * Represents a command for creating a todo task.
 *
 * When executed, it stores the todo task in the taskList and updates the storage.
 * This is not a terminal command.
 *
 * @author Choi Yi Hao
 */
public class TodoCommand extends Command {
    private String info;

    public TodoCommand(String info) {
        this.info = info;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AstorException, IOException {
        String s1 = info.substring(4).trim();
        if (s1.isEmpty()) {
            throw new EmptyTaskInfoException();
        } else {
            Task task = new Todo(s1);
            String s = taskList.addTask(task, storage);
            String output = "Got it. I've added this task:\n  " +
                    s + "\nNow you have " + taskList.size() + " tasks in the list.";
            ui.showOutput(output);
            return output;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
