package talker;

import talker.command.AddCommand;
import talker.command.Command;
import talker.command.DateCommand;
import talker.command.DeleteCommand;
import talker.command.ExitCommand;
import talker.command.FindCommand;
import talker.command.FindPriorityCommand;
import talker.command.ListCommand;
import talker.command.MarkCommand;
import talker.command.SetPriorityCommand;
import talker.command.UnmarkCommand;
import talker.task.Deadline;
import talker.task.Event;
import talker.task.PriorityType;
import talker.task.Task;
import talker.task.TaskType;
import talker.task.ToDo;

/**
 * Represents a parsing object to help parse text inputs
 * into commands and executables
 */
public class Parser {

    /**
     * Parses user input into Command objects depending on the
     * style of user input
     *
     * @param input String input by user
     * @return Command object
     * @throws TalkerException
     */
    public static Command parseInput(String input) throws TalkerException {
        String stripped = input.strip();
        String[] parsed = stripped.split(" ");
        String command = parsed[0].toLowerCase();
        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parsed);
        case "unmark":
            return new UnmarkCommand(parsed);
        case "delete":
            return new DeleteCommand(parsed);
        case "todo":
            return new AddCommand(stripped, TaskType.TODO);
        case "deadline":
            return new AddCommand(stripped, TaskType.DEADLINE);
        case "event":
            return new AddCommand(stripped, TaskType.EVENT);
        case "date":
            return new DateCommand(stripped);
        case "find":
            return new FindCommand(parsed);
        case "setpriority":
            return new SetPriorityCommand(parsed);
        case "findpriority":
            return new FindPriorityCommand(parsed);
        case "bye":
            return new ExitCommand();
        default:
            throw new TalkerException("Unknown command!");
        }
    }

    /**
     * Parses input line from existing file into a Task
     *
     * @param taskString input line from existing file
     * @return Task object represented by the input line
     * @throws TalkerException if invalid input detected
     */
    public static Task parseTaskFromFile(String taskString) throws TalkerException {
        String[] parsed = taskString.split(" \\| ");
        boolean isComplete;
        PriorityType priorityType;

        if (parsed[1].equals("X") || parsed[1].equals(" ")) {
            isComplete = parsed[1].equals("X");
        } else {
            throw new TalkerException("Invalid completion tag, corrupted file detected.");
        }

        if (parsed[2].equals("H")) {
            priorityType = PriorityType.HIGH;
        } else if (parsed[2].equals("M")) {
            priorityType = PriorityType.MEDIUM;
        } else if (parsed[2].equals("L")) {
            priorityType = PriorityType.LOW;
        } else {
            throw new TalkerException("Invalid priority tag, corrupted file detected.");
        }

        switch (parsed[0]) {
        case "T":
            if (parsed.length != 4) {
                throw new TalkerException("Invalid ToDo Task, corrupted file detected.");
            }
            return new ToDo(parsed[3], isComplete, priorityType);
        case "D":
            if (parsed.length != 5) {
                throw new TalkerException("Invalid Deadline Task, corrupted file detected.");
            }
            return new Deadline(parsed[3], parsed[4], isComplete, priorityType);
        case "E":
            if (parsed.length != 6) {
                throw new TalkerException("Invalid Event Task, corrupted file detected.");
            }
            return new Event(parsed[3], parsed[4], parsed[5], isComplete, priorityType);
        default:
            throw new TalkerException("Invalid task type, corrupted file detected.");
        }
    }
}
