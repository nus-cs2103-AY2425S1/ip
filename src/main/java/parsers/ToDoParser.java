package parsers;

import tasks.Task;
import tasks.ToDo;

import exceptions.TarsException;

public class ToDoParser extends Parser {
    @Override
    public Task parse(String[] taskInfo) {
        if (taskInfo.length <= 1) {
            throw new TarsException("Add a name to your tasks.ToDo task");
        }

        String name = taskInfo[1].trim();

        if (name.isEmpty()) {
            throw new TarsException("Try again. Next time add a task name to tell me what you're trying to do");
        }

        return new ToDo(name);
    }

}
