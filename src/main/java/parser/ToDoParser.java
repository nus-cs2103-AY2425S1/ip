package parser;

import exception.IllegalTaskArgumentException;
import parser.TaskParser;
import task.Task;
import task.ToDo;

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
