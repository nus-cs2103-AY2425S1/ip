package moimoi;

import moimoi.command.AddCommand;
import moimoi.command.Command;
import moimoi.command.CommandEnum;
import moimoi.command.DeleteCommand;
import moimoi.command.ExitCommand;
import moimoi.command.FilterCommand;
import moimoi.command.ListCommand;
import moimoi.command.MarkCommand;
import moimoi.command.UnmarkCommand;
import moimoi.exception.InvalidCommandException;
import moimoi.exception.MissingArgumentException;
import moimoi.exception.MoiMoiException;

public class Parser {

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
            case FILTER:
                return new FilterCommand(Parser.extractArgs(commandArgs));
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

    private static String extractArgs(String[] commandArgs) {
        return commandArgs[1];
    }

}
