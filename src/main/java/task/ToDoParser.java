package task;

import exception.IllegalTaskArgumentException;
import exception.IllegalTaskTypeException;

import java.time.LocalDateTime;

public class ToDoParser implements TaskParser {
    @Override
    public Task parse(String taskArgs) throws IllegalTaskArgumentException {
        if (taskArgs.isEmpty()) {
            throw new IllegalTaskArgumentException();
        }
        return new ToDo(taskArgs);
    }
    @Override
    public Task parseFromDb(String taskStr) throws IllegalTaskArgumentException {
        String[] args = taskStr.split(",");
        if (args.length != 3) {
            throw new IllegalTaskArgumentException();
        }
        Task task = new ToDo(args[2]);
        handleTaskStatus(task, args[1]);
        return task;
    }
}
