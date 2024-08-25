package commands;

import java.time.LocalDateTime;

import exceptions.InvalidCommandException;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;
import utility.DateTimeUtility;

public class EventCommand extends Command {
    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        String[] detailsArr = this.input.split(" /from ");
        if (detailsArr.length != 2) {
            throw new InvalidCommandException("Invalid input format." +
                    "Please use the correct format: 'event [task] /from [start time] /to [end time]'");
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
        if (startDate != null && endDate != null) {
            Event event = new Event(description, startDate, endDate);
            taskList.add(event);
        }
    }
}
