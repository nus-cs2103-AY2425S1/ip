package niko.main;

import niko.command.AddCommand;
import niko.command.Command;
import niko.command.DeleteCommand;
import niko.command.ExitCommand;
import niko.command.FindCommand;
import niko.command.ListCommand;
import niko.command.MarkCommand;
import niko.command.SearchCommand;
import niko.command.UnmarkCommand;
import niko.common.NikoException;
import niko.task.Deadline;
import niko.task.Event;
import niko.task.Todo;


/**
 * The Parser class is responsible for parsing user input and creating corresponding Command objects.
 */
public class Parser {
    /**
     * Parses the user's full command input and returns the corresponding Command object.
     *
     * @param fullCommand The full command input by the user.
     * @return The Command object corresponding to the user's input.
     * @throws NikoException If the command is not recognized.
     */
    public static Command parse(String fullCommand) throws NikoException {
        String[] words = fullCommand.split(" ", 2);
        String commandWord = words[0];

        return switch (commandWord) {
            case "todo" -> new AddCommand(new Todo(words[1]));
            case "deadline" -> {
                String[] parts = words[1].split(" /by ");
                yield new AddCommand(new Deadline(parts[0], parts[1]));
            }
            case "event" -> {
                String[] eventParts = words[1].split(" /from | /to ");
                yield new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2]));
            }
            case "list" -> new ListCommand();
            case "bye" -> new ExitCommand();
            case "delete" -> new DeleteCommand(Integer.parseInt(words[1]));
            case "mark" -> new MarkCommand(Integer.parseInt(words[1]));
            case "unmark" -> new UnmarkCommand(Integer.parseInt(words[1]));
            case "search" -> new SearchCommand(words[1]);
            case "find" -> new FindCommand(words[1]);
            default -> throw new NikoException("I'm sorry, but I don't know what that means :-(");
        };
    }
}








