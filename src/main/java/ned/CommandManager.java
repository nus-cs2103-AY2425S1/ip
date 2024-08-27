package ned;

import ned.commands.Command;
import tasks.ToDo;
import ned.exceptions.NedException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

public class CommandManager {
    private static final String FLAG_BYE = "bye";

    public CommandManager(TaskList taskList) {}
    public static Command parse(String userInput) throws NedException{
            CommandTypes command = CommandTypes.findMatchingCommand(userInput);
            switch (command) {
            case UNKNOWN:
                throw new NedException("M'lord, you seem to have given me a nonsensical command." +
                        " Input a correct command, for we have little time! Winter is coming....");
            default:
                return command.getCommand();
            }
    }
    public static Task parseSavedTask(String savedLine) throws NedException{
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
            }
        } catch (IndexOutOfBoundsException e) {
            throw new NedException(String.format("M'lord, it appears that this line: %s is saved in the wrong format.",
                    savedLine));
        } catch (NumberFormatException e) {
            throw new NedException(String.format("M'lord, it appears that this line: %s is saved with an invalid status number.",
                    savedLine));
        }
        return null;
    }

}
