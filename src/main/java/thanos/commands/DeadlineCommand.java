package thanos.commands;

import java.time.LocalDateTime;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Deadline;
import thanos.tasks.TaskList;
import thanos.ui.Ui;
import thanos.utility.DateTimeUtility;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        String[] detailsArr = this.getArgument().split(" /by ");
        if (detailsArr.length != 2) {
            throw new InvalidCommandException(
                    "Invalid input format. Please use the correct format: 'deadline [task] /by [due date]'"
            );
        }

        String description = detailsArr[0];
        LocalDateTime date = DateTimeUtility.parse(detailsArr[1]);
        if (date == null) {
            return;
        }
        Deadline deadline = new Deadline(description, date);
        taskList.add(deadline);
        ui.displayTaskAdded(deadline, taskList.size());
    }
}
