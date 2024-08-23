package matcha.parser;
import matcha.command.AddTaskCommand;
import matcha.command.Command;
import matcha.command.DeleteTaskCommand;
import matcha.command.ExitCommand;
import matcha.command.ListTaskCommand;
import matcha.command.UpdateTaskCommand;
import matcha.exception.MatchaException;
import matcha.task.Deadline;
import matcha.task.Event;
import matcha.task.Task;
import matcha.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Parser object which is responsible for parsing user input and file data.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input User input.
     * @return Command object corresponding to the user input.
     * @throws MatchaException If the user input not a valid Command.
     */
    public static Command parseCommand(String input) throws MatchaException {
        String[] inputWords = input.split(" ", 2);
        String commandType = inputWords[0];

        switch (commandType.toLowerCase()) {

        case "list":
            return new ListTaskCommand();

        case "mark", "unmark":
            return new UpdateTaskCommand(inputWords, commandType);

        case "deadline", "event", "todo":
            return new AddTaskCommand(inputWords, commandType);

        case "delete":
            return new DeleteTaskCommand(inputWords);

        case "bye":
            return new ExitCommand();

        default:
            throw new MatchaException("Hmm, I'm sorry but I am unfamiliar with this command :(");
        }
    }

    /**
     * Parses the data from the file and returns the corresponding Task object.
     *
     * @param data Input from the file.
     * @return Task object corresponding to the data.
     * @throws MatchaException If the data is in the wrong format.
     */
    public static Task parseFileData(String data) throws MatchaException {
        String[] taskInfo = data.split(" \\| ");
        String taskType = taskInfo[0];
        boolean isTaskDone = taskInfo[1].equals("1");
        Task task;

        try {
            switch (taskType) {
                case "T":
                    task = new Todo(taskInfo[2]);
                    if(isTaskDone) {
                        task.markDone();
                    }
                    break;
                case "D":
                    task = new Deadline(taskInfo[2], LocalDateTime.parse(taskInfo[3]));
                    if(isTaskDone) {
                        task.markDone();
                    }
                    break;
                case "E":
                    task = new Event(taskInfo[2], LocalDateTime.parse(taskInfo[3]),
                            LocalDateTime.parse(taskInfo[4]));
                    if(isTaskDone) {
                        task.markDone();
                    }
                    break;
                default:
                    throw new MatchaException("Oh no! Task data was saved in the wrong format.");
            }
        } catch (DateTimeParseException e) {
            throw new MatchaException("Invalid date format! Please format the Date as 'YYYY-MM-DD' and Time as 'HHMM'");
        }

        return task;
    }
}
