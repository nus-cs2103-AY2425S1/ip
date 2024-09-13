package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.IncorrectCommandException;
import derek.task.Task;
import derek.task.TaskList;

import java.time.format.DateTimeParseException;

/**
 * The {@code DeadlineCommand} class adds a deadline task to the task list.
 * It extends the {@code TaskCommand} class and executes the command to add the deadline task.
 */
public class DeadlineCommand extends TaskCommand {

    private Storage storage;
    private Ui ui;
    /**
     * Constructs a {@code DeadlineCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public DeadlineCommand(String command, Storage storage, Ui ui) {

        super(command);
        this.storage = storage;
        this.ui = ui;
    }

    @Override
    public String execute() throws IncorrectCommandException, DateTimeParseException {
        String name = this.getTask();
        String[] information = name.split("/by");
        if (information.length != 2) {
            throw new IncorrectCommandException("Please enter your commands correctly"
                    + "for Derek (deadline (task) /by (date)");
        }

        Task task = Task.deadlineTask(information[0], information[1]);
        TaskList taskList = this.storage.getTaskList();
        taskList.add(task);
        return ui.addTask(task);

    }


}
