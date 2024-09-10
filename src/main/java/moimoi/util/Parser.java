package moimoi.util;

import moimoi.util.command.AddCommand;
import moimoi.util.command.Command;
import moimoi.util.command.CommandEnum;
import moimoi.util.command.DeleteCommand;
import moimoi.util.command.ExitCommand;
import moimoi.util.command.FindCommand;
import moimoi.util.command.ListCommand;
import moimoi.util.command.MarkCommand;
import moimoi.util.command.ScheduleCommand;
import moimoi.util.command.UnmarkCommand;
import moimoi.util.exception.InvalidCommandException;
import moimoi.util.exception.MissingArgumentException;
import moimoi.util.exception.MoiMoiException;

/**
 * The <code>Parser</code> class is responsible for interpreting and processing user input.
 */
public class Parser {

    /**
     * Returns a command corresponding to the specified user input.
     *
     * @param input User input.
     * @return Command corresponding to the specified user input.
     * @throws MoiMoiException If user input corresponds to an invalid command, or a missing argument.
     */
    public static Command parse(String input) throws MoiMoiException {
        try {
            String[] commandArgs = input.split(" ", 2);
            CommandEnum commandEnum = CommandEnum.valueOf(commandArgs[0].toUpperCase());

            switch (commandEnum) {
            case TODO:
            case DEADLINE:
            case EVENT:
                return new AddCommand(commandEnum, Parser.extractArgs(commandArgs));
            case DELETE:
                return new DeleteCommand(Parser.extractArgs(commandArgs));
            case MARK:
                return new MarkCommand(Parser.extractArgs(commandArgs));
            case UNMARK:
                return new UnmarkCommand(Parser.extractArgs(commandArgs));
            case LIST:
                return new ListCommand();
            case SCHEDULE:
                return new ScheduleCommand(Parser.extractArgs(commandArgs));
            case FIND:
                return new FindCommand(Parser.extractArgs(commandArgs));
            case BYE:
                return new ExitCommand();
            default:
                throw new InvalidCommandException();
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException();
        }
    }

    /**
     * Extracts and returns the argument portion of the input.
     *
     * @param commandArgs Input consisting of a command, and an argument portion.
     * @return Argument portion of the input.
     * @throws MissingArgumentException If the argument portion of the input is an empty String.
     */
    private static String extractArgs(String[] commandArgs) throws MissingArgumentException {
        String args = commandArgs[1];

        if (args.isEmpty()) {
            throw new MissingArgumentException();
        }

        return args;
    }

}
