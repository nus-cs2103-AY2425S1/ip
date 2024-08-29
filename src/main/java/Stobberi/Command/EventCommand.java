package Stobberi;

import Stobberi.StobberiException.EmptyStobberiException;
import Stobberi.StobberiException.StobberiException;
import Stobberi.StobberiException.WrongDateTimeStobberiException;

import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private TaskList taskList;
    private String descriptions;
    public EventCommand(TaskList taskList, String descriptions) {
        this.taskList = taskList;
        this.descriptions = descriptions;
    }

    @Override
    public void execute() throws StobberiException {
        if (descriptions.isEmpty()) {
            throw new EmptyStobberiException("Where is the task?");
        }

        String[] eventParts = descriptions.split(" /from ");
        String[] secondParts = eventParts[1].split(" /to ");

        try {
            taskList.addTask(new Event(eventParts[0], secondParts[0], secondParts[1]));
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeStobberiException("Date and Time needs to be in the format dd-MM-yyyy HHmm'hrs'\n Example: 27-12-2004 1700hrs\n");
        }
    }
}