package derek.command;

import derek.*;
import derek.exception.IncorrectCommandException;
import derek.task.Task;
import derek.task.TaskList;

import java.time.format.DateTimeParseException;
import java.util.Date;


/**
 * The {@code EventCommand} class adds an event task to the task list.
 * It extends the {@code TaskCommand} class and executes the command to add the event task.
 */
public class EventCommand extends TaskCommand {

    private Storage storage;
    private Ui ui;

    /**
     * Constructs an {@code EventCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public EventCommand(String command, Storage storage, Ui ui) {
        super(command);
        this.storage = storage;
        this.ui = ui;
    }



    @Override
    public String execute() throws IncorrectCommandException, DateTimeParseException {
        String name = this.getTask();
        String[] taskDescription = name.split("/from");
        String[] time = taskDescription[1].split("/to");
        if (taskDescription.length + time.length != 3) {
            throw new IncorrectCommandException("Please enter your commands correctly "
                    + "for Derek (event (task) /from (time) /to (time)");
        }
        Task task = Task.eventTask(taskDescription[0], time[0], time[1]);
        TaskList taskList = storage.getTaskList();
        taskList.add(task);
        return ui.addTask(task);

    }


}
