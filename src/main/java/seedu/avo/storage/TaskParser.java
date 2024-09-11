package seedu.avo.storage;

import java.time.LocalDateTime;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.Deadline;
import seedu.avo.tasks.Event;
import seedu.avo.tasks.Task;
import seedu.avo.tasks.ToDo;
import seedu.avo.utils.DateTime;

/**
 * Represents a helper class to parse contents of file storage
 */
public class TaskParser extends FileParser<Task> {
    @Override
    public Task parse(String input) throws AvoException {
        String[] inputs = input.split(" : ");
        assert inputs.length > 0 : "inputs should not be empty";
        String type = inputs[0];
        Task task;
        switch (type) {
        case "T":
            task = new ToDo(inputs[2]);
            break;
        case "D":
            LocalDateTime dueDate = DateTime.parseFromStorage(inputs[3]);
            task = new Deadline(inputs[2], dueDate);
            break;
        case "E":
            LocalDateTime startTime = DateTime.parseFromStorage(inputs[3]);
            LocalDateTime endTime = DateTime.parseFromStorage(inputs[4]);
            task = new Event(inputs[2], startTime, endTime);
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
