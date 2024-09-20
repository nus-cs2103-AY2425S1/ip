package mryapper.command;

import mryapper.exception.InvalidSyntaxException;
import mryapper.parser.DateTimeParser;
import mryapper.storage.Storage;
import mryapper.task.Event;
import mryapper.task.Task;
import mryapper.task.TaskList;

/**
 * A command which deletes the task from the task list.
 */
public class AddEvent extends Command {
    public static final String SYNTAX = "e.g. event project meeting /from Mon 2pm /to 4pm";
    private final String description;
    private final String startTime;
    private final String endTime;

    public AddEvent(String description, String start, String end) {
        this.description = description;
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidSyntaxException {
        assert !description.isEmpty(): "description should not be empty";
        assert !startTime.isEmpty(): "start time/date should not be empty";
        assert !endTime.isEmpty(): "end time/date should not be empty";

        Task newTask = new Event(description,
                DateTimeParser.parseDateTime(startTime),
                DateTimeParser.parseDateTime(endTime));
        tasks.add(newTask);
        tasks.saveToStorage(storage);
        String response = String.format("Task added successfully!\n  %s\n"
                        + "Now you have %d tasks in the list",
                newTask, tasks.count());

        return response;
    }
}
