package matcha.parser;
import matcha.command.*;
import matcha.exception.MatchaException;
import matcha.task.Deadline;
import matcha.task.Event;
import matcha.task.Task;
import matcha.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {
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
