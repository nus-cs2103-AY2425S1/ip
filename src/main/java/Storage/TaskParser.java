package Storage;

import Exceptions.AvoException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import Utils.DateTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskParser extends FileParser<Task> {
    @Override
    public Task parse(String input) throws AvoException {
        String[] inputs = input.split(" : ");
        String type = inputs[0];
        Task task;
        switch (type) {
            case "T":
                task = new ToDo(inputs[2]);
                break;
            case "D":
                LocalDate dueDate = DateTime.parse(inputs[3], DateTimeFormatter.ofPattern("MMM d yyyy"));
                task =  new Deadline(inputs[2], dueDate);
                break;
            case "E":
                LocalDate startTime = DateTime.parse(inputs[3], DateTimeFormatter.ofPattern("MMM d yyyy"));
                LocalDate endTime = DateTime.parse(inputs[4], DateTimeFormatter.ofPattern("MMM d yyyy"));
                task =  new Event(inputs[2], startTime, endTime);
                break;
            default:
                throw new AvoException("Invalid task type in storage");
        }
        boolean isCompleted = Integer.parseInt(inputs[1]) == 1;
        if (isCompleted) {
            task.complete();
        }
        return task;
    }
}
