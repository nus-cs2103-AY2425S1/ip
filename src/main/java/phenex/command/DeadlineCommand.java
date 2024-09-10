package phenex.command;

import java.time.LocalDate;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.Deadline;
import phenex.task.TaskList;
import phenex.ui.Ui;

/**
 * DeadlineCommand class which encapsulates Command to create a Deadline task.
 */
public class DeadlineCommand extends CreateTaskCommand {
    /** encapsulates the date of deadline */
    private LocalDate localDate;

    public DeadlineCommand() {
        super("");
    }

    public DeadlineCommand(String name) {
        super(name);
    }

    public void setDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        Deadline deadline = new Deadline(this.name, this.localDate);
        if (taskList.containsTask(deadline)) {
            throw new PhenexException("Error: Duplicate Mission. Aborting!");
        }
        taskList.addTask(deadline);
        String stringToReturn = ui.printTaskAddedMessage(deadline, taskList.getTasks().size());
        return stringToReturn;
    }
}
