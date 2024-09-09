package ned;

import ned.commands.Command;
import ned.exceptions.InvalidCacheLineException;
import ned.exceptions.NedException;
import ned.exceptions.UnknownCommandException;
import ned.tasks.Deadline;
import ned.tasks.Event;
import ned.tasks.Task;
import ned.tasks.ToDo;

/**
 * Represents the parser class, which handles interpreting user input into commands, which are then ran.
 */
public class Parser {

    private static final String PARSER_UNKNOWN_COMMAND_ERROR_MESSAGE = "M'lord, you seem to have given me a "
            + "nonsensical command. Input a correct command, for we have little time! Winter is coming....";

    /**
     * Constructs an object which is responsible for converting input from users into commands and their parameters
     */
    public Parser() {
    }

    /**
     * @param userInput String representing user input
     * @return A command object, which can then be executed
     * @throws NedException Thrown if user input does not find any known command
     */
    public static Command parse(String userInput) throws NedException {
        CommandTypes command = CommandTypes.findMatchingCommand(userInput);
        switch (command) {
        case UNKNOWN:
            throw new UnknownCommandException(PARSER_UNKNOWN_COMMAND_ERROR_MESSAGE);
        default:
            return command.getCommand();
        }
    }

    /**
     * Parses lines from the cached list of tasks, and converts them into Task objects. Returns null if the line
     * cannot be converted into a task.
     *
     * @param savedLine Line from the cached list of tasks
     * @return Task objects
     * @throws NedException if the line is not in the proper format or if the status number (done or not done) is not
     *                      a 0 or 1
     */
    public static Task parseSavedTask(String savedLine) throws NedException {
        // uses CSV
        String[] splitLine = savedLine.split("\\|");
        String taskType = splitLine[0];
        try {
            int taskStatus = Integer.parseInt(splitLine[1]);
            boolean isTaskDone = taskStatus != 0;
            switch (taskType) {
            case "todo":
                return ToDo.createToDo(splitLine[2], isTaskDone);
            case "deadline":
                return Deadline.createDeadline(splitLine[2], splitLine[3], isTaskDone);
            case "event":
                return Event.createEvent(splitLine[2], splitLine[3], splitLine[4], isTaskDone);
            default:
                throw new UnknownCommandException(String.format("M'lord, it appears that this line: %s is an unknown "
                        + "task type.", savedLine));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCacheLineException(String.format("M'lord, it appears that this line: %s is saved in the "
                    + "wrong format.", savedLine));
        } catch (NumberFormatException e) {
            throw new InvalidCacheLineException(
                    String.format("M'lord, it appears that this line: %s is saved with an invalid status number."
                            , savedLine));
        }
    }
}
