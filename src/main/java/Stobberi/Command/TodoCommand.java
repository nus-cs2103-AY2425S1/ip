package Stobberi.Command;

import Stobberi.StobberiException.EmptyStobberiException;
import Stobberi.StobberiException.StobberiException;
import Stobberi.components.TaskList;
import Stobberi.Task.Todo;

public class TodoCommand extends Command {
    private TaskList taskList;
    private String descriptions;
    public TodoCommand(TaskList taskList, String descriptions) {
        this.taskList = taskList;
        this.descriptions = descriptions;
    }

    @Override
    public void execute() throws StobberiException {
        if (descriptions.isEmpty()) {
            throw new EmptyStobberiException("Where is the task?");
        }
        taskList.addTask(new Todo(descriptions));
    }
}