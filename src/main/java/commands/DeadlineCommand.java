package commands;

import java.time.LocalDateTime;

import exceptions.InvalidCommandException;
import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;
import utility.DateTimeUtility;

public class DeadlineCommand extends Command {
    private final String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        String[] detailsArr = this.input.split(" /by ");
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
