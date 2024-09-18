package mryapper.command;

import mryapper.parser.DateTimeParser;
import mryapper.storagemanager.StorageManager;
import mryapper.task.Event;
import mryapper.task.Task;
import mryapper.task.TaskList;

/**
 * A command which deletes the task from the task list.
 */
public class AddEvent extends Command {
    private final String description;
    private final String startTime;
    private final String endTime;

    public AddEvent(String description, String start, String end) {
        this.description = description;
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String execute(TaskList tasks, StorageManager storage) {
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

    /**
     * Gets the syntax of the command.
     *
     * @return The syntax of the command to event command.
     */
    public static String getSyntax() {
        return "e.g. event project meeting /from Mon 2pm /to 4pm";
    }
}
