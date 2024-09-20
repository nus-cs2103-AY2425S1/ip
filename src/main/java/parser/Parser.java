package parser;

import commands.*;

import exceptions.HandsomeException;
import task.Deadline;
import task.Event;
import task.ToDo;

/**
 * Scans through the command that the users give.
 */
public class Parser {
    /**
     * Starts the parsing process.
     *
     * @param input registers the user's commands.
     * @return the corresponding actions for the command.
     * @throws HandsomeException for handsome specific exceptions.
     */
    public static Command parse(String input) throws HandsomeException {
        assert !input.isEmpty() : "Hey, the input should not be empty haha.";
        if (input.trim().isEmpty()) {
            throw new HandsomeException("Hey, the input should not be empty haha.");
        }

        String[] specifiedInput = input.split(" ", 2); // for mark 2 etc.
        String command = specifiedInput[0];

        switch (command) {
            case "list":
                return new ListCommand();

            case "mark":
                return new MarkCommand(Integer.parseInt(specifiedInput[1]) - 1);

            case "unmark":
                return new UnmarkCommand(Integer.parseInt(specifiedInput[1]) - 1);

            case "todo":
                if (specifiedInput.length < 2 || specifiedInput[1].trim().isEmpty()) {
                    throw new HandsomeException("Hey man... give me a descriptor to work with?");
                }
                return new AddCommand(new ToDo(specifiedInput[1]));

            case "deadline":
                if (specifiedInput.length < 2 || specifiedInput[1].trim().isEmpty()) {
                    throw new HandsomeException("Hey man... give me a descriptor to work with?");
                }
                String[] taskAndTime = specifiedInput[1].split(" /by ", 2);
                return new AddCommand(new Deadline(taskAndTime[0], taskAndTime[1]));

            case "event":
                if (specifiedInput.length < 2 || specifiedInput[1].trim().isEmpty()) {
                    throw new HandsomeException("Hey man... give me a descriptor to work with?");
                }
                String[] fromAndTo = specifiedInput[1].split(" /from | /to ");
                return new AddCommand(new Event(fromAndTo[0], fromAndTo[1], fromAndTo[2]));

            case "delete":
                return new DeleteCommand(Integer.parseInt(specifiedInput[1]) - 1);

            case "find":
                if (specifiedInput.length < 2 || specifiedInput[1].trim().isEmpty()) {
                    throw new HandsomeException("Hey.. please give me a keyword to search for.");
                }
                return new FindCommand(specifiedInput[1]);

            case "bye":
                return new ExitCommand();

            case "undo":
                return new UndoCommand();

            default:
                System.out.println("Hey handsome, unfortunately I don't know what you are saying :( ");
                throw new HandsomeException("I don't quite understand you, let's revisit the docs?");
        }
    }
}
