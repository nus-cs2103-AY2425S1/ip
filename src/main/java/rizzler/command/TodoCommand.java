package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;
import rizzler.task.ToDo;

public class TodoCommand extends Command {
    private final ToDo newTodo;

    public TodoCommand(String todoDesc) {
        super();
        this.newTodo = new ToDo(todoDesc);
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        taskLog.addTask(newTodo);
        storage.storeTasks(taskLog);
        return new String[] {"certainly, i'll keep track of this todo for you ;)",
                "\t" + newTodo,
                "now we have " + taskLog.getNumTasks() + " tasks to work on."};
    }
}
