package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.Deadline;
import phenex.task.TaskList;
import phenex.task.ToDo;
import phenex.ui.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends CreateTaskCommand {
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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        Deadline deadline = new Deadline(this.name, this.localDate);
        taskList.addTask(deadline);
        ui.printTaskAddedMessage(deadline, taskList.getTasks().size());
    }
}
