package duck.parser;

import duck.commands.ByeCommand;
import duck.commands.Command;
import duck.commands.CommandWord;
import duck.commands.DeadlineCommand;
import duck.commands.DeleteCommand;
import duck.commands.EventCommand;
import duck.commands.FindCommand;
import duck.commands.HelpCommand;
import duck.commands.InvalidCommand;
import duck.commands.ListCommand;
import duck.commands.MarkCommand;
import duck.commands.OnCommand;
import duck.commands.SortCommand;
import duck.commands.ToDoCommand;
import duck.commands.UnmarkCommand;
import duck.common.Utils;

/**
 * Parses user input and creates corresponding Command instances based on the input message.
 */
public class Parser {

    /**
     * Parses the given message and returns the appropriate Command based on the instruction in the message.
     *
     * @param message The user input message to parse.
     * @return A Command instance corresponding to the instruction in the message.
     *         Returns an InvalidCommand if the instruction is not recognized.
     */
    public static Command parse(String message) {
        try {
            // Trim and get instruction word
            String trimmedMessage = Utils.trimExtraSpaces(message);
            String instructionWord = getInstruction(trimmedMessage);
            CommandWord instruction = CommandWord.valueOf(instructionWord);

            //CHECKSTYLE.OFF: Indentation
            return switch (instruction) {
                case HELP -> new HelpCommand(trimmedMessage);
                case LIST -> new ListCommand(trimmedMessage);
                case FIND -> new FindCommand(trimmedMessage);
                case SORT -> new SortCommand(trimmedMessage);
                case MARK -> new MarkCommand(trimmedMessage);
                case UNMARK -> new UnmarkCommand(trimmedMessage);
                case DELETE -> new DeleteCommand(trimmedMessage);
                case TODO -> new ToDoCommand(trimmedMessage);
                case DEADLINE -> new DeadlineCommand(trimmedMessage);
                case EVENT -> new EventCommand(trimmedMessage);
                case ON -> new OnCommand(trimmedMessage);
                case BYE -> new ByeCommand(trimmedMessage);
            };
            //CHECKSTYLE.ON: Indentation
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("");
        }
    }

    /**
     * Extracts the instruction part from the given message.
     * The instruction is the first word of the message, converted to uppercase.
     *
     * @param message The user input message.
     * @return The instruction part of the message in uppercase.
     */
    private static String getInstruction(String message) {

        return message.split(" ")[0].toUpperCase();
    }
}
