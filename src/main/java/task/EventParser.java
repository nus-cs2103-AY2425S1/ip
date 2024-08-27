package task;

import exception.IllegalTaskArgumentException;

import java.time.LocalDateTime;

public class EventParser implements TaskParser {
    @Override
    public Task parse(String taskArgs) throws IllegalTaskArgumentException {
        if (!taskArgs.contains(" /from ") || !taskArgs.contains(" /to ")) {
            throw new IllegalTaskArgumentException();
        }
        String[] args = taskArgs.split(" /from | /to ", 3);
        LocalDateTime from = stringToDateTime(args[1].trim());
        LocalDateTime to = stringToDateTime(args[2].trim());
        return new Event(args[0].trim(), from, to);
    }
    @Override
    public Task parseFromDb(String taskStr) throws IllegalTaskArgumentException {
        String[] args = taskStr.split(",");
        if (args.length != 5) {
            throw new IllegalTaskArgumentException();
        }
        LocalDateTime from = dbStringToDateTime(args[3].trim());
        LocalDateTime to = dbStringToDateTime(args[4].trim());
        Task task = new Event(args[2], from, to);
        handleTaskStatus(task, args[1]);
        return task;
    }
}