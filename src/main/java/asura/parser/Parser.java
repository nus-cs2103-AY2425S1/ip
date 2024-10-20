package asura.parser;

import asura.commands.*;
import asura.data.exception.AsuraException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents a parser to parse the user commands.
 */
public class Parser {

    public Parser() {
    }

    /**
     * Identifies the command of the user and returns the command identified.
     * @param command The command that the user inputted.
     * @return The command identified by the parser.
     * @throws AsuraException If the user inputted an invalid command.
     */
    public static Command parse(String command) throws AsuraException {
        List<String> splitCommand = Arrays.asList(command.split(" "));
        List<String> singletonCommands = Arrays.asList("list", "bye", "help");
        assert !splitCommand.isEmpty() : "splitCommand should not be empty";
        String prefix = splitCommand.get(0);
        if (splitCommand.size() == 1 && !singletonCommands.contains(prefix)) {
            throw new AsuraException("Invalid command format. Please type 'help' to view the correct command formats.");
        }
        int selection;
        String taskString = String.join(" ", splitCommand.subList(1, splitCommand.size()));

        return switch (prefix) {
            case "list" -> new ListCommand();
            case "mark" -> {
                selection = Integer.parseInt(splitCommand.get(1)) - 1;
                yield new MarkCommand(selection);
            }
            case "unmark" -> {
                selection = Integer.parseInt(splitCommand.get(1)) - 1;
                yield new UnmarkCommand(selection);
            }
            case "todo" -> new TodoCommand(taskString);
            case "deadline" -> parseDeadline(splitCommand);
            case "event" -> parseEvent(splitCommand);
            case "delete" -> {
                selection = Integer.parseInt(splitCommand.get(1)) - 1;
                yield new DeleteCommand(selection);
            }
            case "bye" -> new ByeCommand();
            case "find" -> new FindCommand(taskString);
            case "help" -> new HelpCommand();
            default -> throw new AsuraException("Invalid input");
        };
    }

    private static DeadlineCommand parseDeadline(List<String> splitCommand) throws AsuraException {
        List<String> descriptionArray;
        int byIndex = splitCommand.indexOf("/by");
        descriptionArray = splitCommand.subList(1, byIndex);
        if (descriptionArray.isEmpty()) {
            throw new AsuraException("The description todo cannot be empty.");
        }
        List<String> dateArray = splitCommand.subList(byIndex + 1, splitCommand.size());
        if (dateArray.isEmpty()) {
            throw new AsuraException("The date cannot be empty.");
        }

        return new DeadlineCommand(descriptionArray, dateArray);
    }

    private static EventCommand parseEvent(List<String> splitCommand) throws AsuraException {
        List<String> descriptionArray;
        int fromIndex = splitCommand.indexOf("/from");
        int toIndex = splitCommand.indexOf("/to");
        descriptionArray = splitCommand.subList(1, fromIndex);
        if (descriptionArray.isEmpty()) {
            throw new AsuraException("The description todo cannot be empty.");
        }
        List<String> fromArray = splitCommand.subList(fromIndex + 1, toIndex);
        if (fromArray.isEmpty()) {
            throw new AsuraException("The from date cannot be empty.");
        }
        List<String> toArray = splitCommand.subList(toIndex + 1, splitCommand.size());
        if (toArray.isEmpty()) {
            throw new AsuraException("The to date cannot be empty.");
        }
        return new EventCommand(descriptionArray, fromArray, toArray);
    }
}
