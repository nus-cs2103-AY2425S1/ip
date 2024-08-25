package hana.parser;

import hana.HanaException;
import hana.command.ByeCommand;
import hana.command.Command;
import hana.command.DeadlineCommand;
import hana.command.DeleteCommand;
import hana.command.EventCommand;
import hana.command.FindByDateCommand;
import hana.command.FindCommand;
import hana.command.HelpCommand;
import hana.command.ListCommand;
import hana.command.MarkCommand;
import hana.command.ToDoCommand;
import hana.command.UnmarkCommand;

/**
 * Deals with making sense of the user command
 */
public class Parser {

    /**
     * Use input and return the command to execute.
     *
     * @param input
     * @return Command to execute.
     * @throws HanaException If input is not one of the available commands.
     */
    public static Command parse(String input) throws HanaException {
        if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("todo")) {
            return new ToDoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new EventCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("bye")) {
            return new ByeCommand();
        } else if (input.startsWith("list")) {
            return new ListCommand();
        } else if (input.startsWith("findByDate")) {
            return new FindByDateCommand(input);
        } else if (input.startsWith("help")) {
            return new HelpCommand();
        } else if (input.startsWith("findByKey")) {
            return new FindCommand(input);
        } else {
            throw new HanaException("Unknown command! Use help to see list of available commands");
        }
    }
}
