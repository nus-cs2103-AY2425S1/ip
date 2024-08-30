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


    public static String parseTaskInfo(Task task) {
        String localDateString = "";
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            localDateString = deadlineTask.localDate.toString() + ", ";
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            localDateString = eventTask.startDate.toString() + ", " + eventTask.endDate.toString() + ", ";
        }
        return task.symbol + ", "
                + (task.isCompleted ? "1, " : "0, ")
                + task.name + ", "
                + localDateString
                + "\n";
    }
}
