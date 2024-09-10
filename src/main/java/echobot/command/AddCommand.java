package echobot.command;

import echobot.exception.DeadlineEmptyException;
import echobot.exception.EchoBotException;
import echobot.exception.EventStartEndDateEmptyException;
import echobot.exception.InvalidDeadlineFormatException;
import echobot.exception.TaskNameEmptyException;
import echobot.task.Deadline;
import echobot.task.ToDo;
import echobot.task.Task;
import echobot.task.Event;

public class AddCommand extends Command implements Undoable {
    public final static String COMMAND = "add";
    public final static String TODO_COMMAND = "todo";
    public final static String DEADLINE_COMMAND = "deadline";
    public final static String EVENT_COMMAND = "event";
    private final CommandType commandType = CommandType.ADD;

    private final Task taskToAdd;
    private int insertIndex = -1;

    public AddCommand(String description) throws TaskNameEmptyException {
        this.taskToAdd = new ToDo(false, description);
    }

    public AddCommand(String description, String deadline) throws InvalidDeadlineFormatException, TaskNameEmptyException, DeadlineEmptyException {
        this.taskToAdd = new Deadline(false, description, deadline);
    }

    public AddCommand(String description, String from, String to) throws InvalidDeadlineFormatException, TaskNameEmptyException, EventStartEndDateEmptyException {
        this.taskToAdd = new Event(false, description, from, to);
    }

    public AddCommand(Task task, int insertIndex) {
        this.taskToAdd = task;
        this.insertIndex = insertIndex;
    }

    @Override
    public CommandResponse execute() throws EchoBotException {
        if (this.insertIndex == -1) {
            taskList.addTask(this.taskToAdd);
        } else {
            taskList.addTask(this.taskToAdd, this.insertIndex);
        }
        String response = "Got it. I've added this task:\n\t\t\t\t" + this.taskToAdd + "\n\t\t\tNow you have " + this.taskList.size() + " task(s) in the list.";
        fileManagement.save();
        return new CommandResponse(this.commandType, response);
    }

    @Override
    public CommandResponse undo() throws EchoBotException {
        final DeleteCommand deleteCommand = new DeleteCommand(this.taskList.size());
        deleteCommand.setTaskList(super.taskList);
        deleteCommand.setFileManagement(super.fileManagement);
        deleteCommand.setCommandHistoryList(super.commandHistoryList);
        return deleteCommand.execute();
    }
}
