package task;

import exception.IllegalTaskArgumentException;

import java.time.LocalDateTime;

public class DeadlineParser implements TaskParser {
    @Override
    public Task parse(String taskArgs) throws IllegalTaskArgumentException {
        if (!taskArgs.contains(" /by ")) {
            throw new IllegalTaskArgumentException();
        }
        String[] args = taskArgs.split(" /by ", 2);
        LocalDateTime deadline = stringToDateTime(args[1].trim());
        return new Deadline(args[0].trim(), deadline);
    }
    @Override
    public Task parseFromDb(String taskStr) throws IllegalTaskArgumentException {
        String[] args = taskStr.split(",");
        if (args.length != 4) {
            throw new IllegalTaskArgumentException();
        }
        LocalDateTime deadline = dbStringToDateTime(args[3].trim());
        Task task = new Deadline(args[2], deadline);
        handleTaskStatus(task, args[1]);
        return task;
    }
}
