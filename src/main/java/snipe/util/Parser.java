package snipe.util;

import snipe.command.*;
import snipe.exception.SnipeException;
import snipe.task.Deadline;
import snipe.task.Event;
import snipe.task.ToDo;

public class Parser {
    public static Command parse(String userInput) throws SnipeException {
        String[] split = userInput.split(" ", 2);
        String commandWord = split[0].toUpperCase();

        switch (commandWord) {
        case "TODO":
        case "DEADLINE":
        case "EVENT":
            return parseTaskCommand(commandWord, split);
        case "DELETE":
            if (split.length < 2 || split[1].trim().isEmpty()) {
                throw new SnipeException("Please input a number. Use 'help' for correct syntax");
            }
            return new DeleteCommand(Integer.parseInt(split[1]) - 1);
        case "MARK":
            if (split.length < 2 || split[1].trim().isEmpty()) {
                throw new SnipeException("Please input a number. Use 'help' for correct syntax");
            }
            return new MarkCommand(Integer.parseInt(split[1]) - 1);
        case "UNMARK":
            if (split.length < 2 || split[1].trim().isEmpty()) {
                throw new SnipeException("Please input a number. Use 'help' for correct syntax");
            }
            return new UnmarkCommand(Integer.parseInt(split[1]) - 1);
        case "FIND":
            if (split.length < 2 || split[1].trim().isEmpty()) {
                throw new SnipeException("Please input keyword. Use 'help' for correct syntax");
            }
            return new FindCommand(split[1]);
        case "HELP":
            return new HelpCommand();
        case "LIST":
            return new ListCommand();
        case "BYE":
            return new ExitCommand();
        default:
            throw new SnipeException("I'm sorry, I don't understand that command.");
        }
    }

    private static Command parseTaskCommand(String commandWord, String[] split) throws SnipeException {
        if (split.length < 2 || split[1].trim().isEmpty()) {
            throw new SnipeException("The description of a task cannot be empty. Use 'help' for correct syntax.");
        }

        String taskDescription = split[1];
        switch (commandWord) {
        case "TODO":
            return new AddCommand(new ToDo(taskDescription));
        case "DEADLINE":
            String[] deadlineSplit = taskDescription.split(" /by ", 2);
            if (deadlineSplit.length < 2) {
                throw new SnipeException("A deadline requires a description and a /by date.");
            }
            return new AddCommand(new Deadline(deadlineSplit[0], deadlineSplit[1]));
        case "EVENT":
            String[] eventSplit = taskDescription.split(" /from | /to ");
            if (eventSplit.length < 3) {
                throw new SnipeException("An event requires a description, a /from date, and a /to date.");
            }
            return new AddCommand(new Event(eventSplit[0], eventSplit[1], eventSplit[2]));
        default:
            throw new SnipeException("Invalid task type.");
        }
    }
}
