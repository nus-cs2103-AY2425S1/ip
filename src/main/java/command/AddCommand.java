package command;

import exception.DeadlineEmptyException;
import exception.EchoBotException;
import exception.EventStartEndDateEmptyException;
import exception.InvalidDeadlineFormatException;
import exception.TaskNameEmptyException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class AddCommand extends Command {
    public final static String COMMAND = "add";
    public final static String TODO_COMMAND = "todo";
    public final static String DEADLINE_COMMAND = "deadline";
    public final static String EVENT_COMMAND = "event";

    private final Task taskToAdd;

    public AddCommand(String description) throws TaskNameEmptyException {
        this.taskToAdd = new ToDo(false, description);
    }

    public AddCommand(String description, String deadline) throws InvalidDeadlineFormatException, TaskNameEmptyException, DeadlineEmptyException {
        this.taskToAdd = new Deadline(false, description, deadline);
    }

    public AddCommand(String description, String from, String to) throws InvalidDeadlineFormatException, TaskNameEmptyException, EventStartEndDateEmptyException {
        this.taskToAdd = new Event(false, description, from, to);
    }

    @Override
    public CommandResponse execute() throws EchoBotException {
        taskList.addTask(this.taskToAdd);
        String response = "Got it. I've added this task:\n\t\t\t\t" + this.taskToAdd + "\n\t\t\tNow you have " + this.taskList.size() + " task(s) in the list.";
        fileManagement.save();
        return new CommandResponse(response);
    }
}
