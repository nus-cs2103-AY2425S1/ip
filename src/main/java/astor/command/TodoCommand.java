package astor.command;

import java.io.IOException;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.AstorException;
import astor.exception.EmptyTaskInfoException;
import astor.task.Task;
import astor.task.Todo;


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
        assert taskList != null : "taskList must not be null";
        assert ui != null : "ui must not be null";
        assert storage != null : "storage must not be null";

        String s1 = info.substring(4).trim();
        if (s1.isEmpty()) {
            throw new EmptyTaskInfoException();
        } else {
            Task task = new Todo(s1);
            String output = taskList.addTask(task, storage);
            ui.showOutput(output);
            return output;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
