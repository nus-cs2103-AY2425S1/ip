package darwin.parser;

import darwin.exception.IllegalTaskArgumentException;
import darwin.task.Task;
import darwin.task.ToDo;

public class ToDoParser implements TaskParser {
    @Override
    public Task parse(String taskArgs) throws IllegalTaskArgumentException {
        if (taskArgs.isEmpty()) {
            throwIllegalTaskArgumentException();
        }
        return new ToDo(taskArgs);
    }

    @Override
    public Task parseFromDb(String taskStr) throws IllegalTaskArgumentException {
        String[] args = taskStr.split(",");
        if (args.length != 3) {
            throwIllegalTaskArgumentException();
        }
        Task task = new ToDo(args[2]);
        handleTaskStatus(task, args[1]);
        return task;
    }
}
