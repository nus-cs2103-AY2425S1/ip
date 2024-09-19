package mryapper.command;

import mryapper.parser.DateTimeParser;
import mryapper.storage.Storage;
import mryapper.task.Deadline;
import mryapper.task.Task;
import mryapper.task.TaskList;

/**
 * A command which creates a task with deadline.
 */
public class AddDeadline extends Command {
    public static final String SYNTAX = "e.g. deadline CS2103T project /by Dec 31st";
    private final String description;
    private final String deadline;

    public AddDeadline(String description, String by) {
        this.description = description;
        this.deadline = by;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert !description.isEmpty(): "description should not be empty";
        assert !deadline.isEmpty(): "deadline should not be empty";

        Task newTask = new Deadline(description, DateTimeParser.parseDateTime(deadline));
        tasks.add(newTask);
        tasks.saveToStorage(storage);
        String response = String.format("Task added successfully!\n  %s\n"
                + "Now you have %d tasks in the list",
                newTask, tasks.count());

        return response;
    }
}
