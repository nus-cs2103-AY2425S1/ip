package thanos.commands;

import java.time.LocalDateTime;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Event;
import thanos.tasks.TaskList;
import thanos.ui.Ui;
import thanos.utility.DateTimeUtility;

public class EventCommand extends Command {
    public EventCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        String[] detailsArr = this.getArgument().split(" /from ");
        if (detailsArr.length != 2) {
            throw new InvalidCommandException("Invalid input format."
                    + "Please use the correct format: 'event [task] /from [start time] /to [end time]'");
        }

        String description = detailsArr[0];
        String[] fromToArr = detailsArr[1].split(" /to ");
        if (fromToArr.length != 2) {
            throw new InvalidCommandException(
                    "Invalid input format. Please ensure both start and end times are provided."
            );
        }

        LocalDateTime startDate = DateTimeUtility.parse(fromToArr[0]);
        LocalDateTime endDate = DateTimeUtility.parse(fromToArr[1]);
        if (startDate == null || endDate == null) {
            return;
        }
        Event event = new Event(description, startDate, endDate);
        taskList.add(event);
        ui.displayTaskAdded(event, taskList.size());
    }
}
