package matcha.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import matcha.command.AddTaskCommand;
import matcha.command.Command;
import matcha.command.DeleteTaskCommand;
import matcha.command.ExitCommand;
import matcha.command.FindTaskCommand;
import matcha.command.ListTaskCommand;
import matcha.command.UpdateTaskCommand;
import matcha.exception.MatchaException;
import matcha.task.Deadline;
import matcha.task.Event;
import matcha.task.Task;
import matcha.task.Todo;



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

        case "find":
            return new FindTaskCommand(inputWords);

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
        boolean isTaskDone = taskInfo[1].equals("1");
        Task task;
        try {
            task = createParsedTask(taskInfo);
            if (isTaskDone) {
                task.markDone();
            }
        } catch (DateTimeParseException e) {
            throw new MatchaException("Invalid date format! Please format the Date as 'YYYY-MM-DD' and Time as 'HHMM'");
        }
        return task;
    }

    /**
     * Creates a Task object based on the parsed data from the file.
     *
     * @param taskInfo Parsed data on the task from the file.
     * @return  Task object based on the parsed data.
     * @throws MatchaException If the task data is saved in the wrong format.
     */
    private static Task createParsedTask(String[] taskInfo) throws MatchaException {
        String taskType = taskInfo[0];
        String taskDescription = taskInfo[2];
        switch (taskType) {
        case "T":
            return new Todo(taskDescription);
        case "D":
            String taskDueDate = taskInfo[3];
            return new Deadline(taskDescription, LocalDateTime.parse(taskDueDate));
        case "E":
            String taskStartDate = taskInfo[3];
            String taskEndDate = taskInfo[4];
            return new Event(taskDescription, LocalDateTime.parse(taskStartDate), LocalDateTime.parse(taskEndDate));
        default:
            throw new MatchaException("Oh no! Task data was saved in the wrong format.");
        }
    }
}
