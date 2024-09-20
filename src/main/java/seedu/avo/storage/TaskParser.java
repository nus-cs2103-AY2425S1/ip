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
        Task task = getTaskType(inputs);
        boolean isCompleted = Integer.parseInt(inputs[1]) == 1;
        if (isCompleted) {
            task.complete();
        }
        return task;
    }

    private Task getTaskType(String[] inputs) throws AvoException {
        String type = inputs[0];
        switch (type) {
        case "T":
            return new ToDo(inputs[2]);
        case "D":
            LocalDateTime dueDate = DateTime.parseFromStorage(inputs[3]);
            return new Deadline(inputs[2], dueDate);
        case "E":
            LocalDateTime startTime = DateTime.parseFromStorage(inputs[3]);
            LocalDateTime endTime = DateTime.parseFromStorage(inputs[4]);
            return new Event(inputs[2], startTime, endTime);
        default:
            throw new AvoException("Invalid task type in storage");
        }
    }
}
