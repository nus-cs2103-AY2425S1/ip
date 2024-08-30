package phenex.util;

import phenex.exception.PhenexException;
import phenex.Phenex;
import phenex.task.Deadline;
import phenex.task.Event;
import phenex.task.Task;

import java.util.regex.Matcher;

public class Parser {

    public int getIndexOfTask(Matcher matcher, Phenex.CommandType commandType) throws PhenexException {
        int indexOfResult;
        switch (commandType) {
        case COMMAND_DELETE:
            indexOfResult = 7;
            break;
        case COMMAND_MARK:
            indexOfResult = 5;
            break;
        case COMMAND_UNMARK:
            indexOfResult = 7;
            break;
        default:
            throw new PhenexException("Unknown commandType! ABORTING");
        }
        return Integer.parseInt(matcher.group().substring(indexOfResult)) - 1;
    }

    public String getNameOfTask(Matcher matcher) {
        return matcher.group().substring(5);
    }


    public static String parseTaskInfo(Task task) {
        String localDateString = "";
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            localDateString = deadlineTask.getDeadlineDate().toString() + ", ";
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            localDateString = eventTask.getEventStartDate().toString()
                    + ", " + eventTask.getEventEndDate().toString() + ", ";
        }
        return task.getSymbol() + ", "
                + (task.isCompleted() ? "1, " : "0, ")
                + task.getName() + ", "
                + localDateString
                + "\n";
    }
}
