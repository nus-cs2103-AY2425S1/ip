package moimoi;

import moimoi.command.AddCommand;
import moimoi.command.Command;
import moimoi.command.CommandEnum;
import moimoi.command.DeleteCommand;
import moimoi.command.ExitCommand;
import moimoi.command.FilterCommand;
import moimoi.command.FindCommand;
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
            Command command;

            switch (commandEnum) {
            case TODO:
            case DEADLINE:
            case EVENT:
                command = new AddCommand(commandEnum, Parser.extractArgs(commandArgs));
                break;
            case DELETE:
                command = new DeleteCommand(Parser.extractArgs(commandArgs));
                break;
            case MARK:
                command = new MarkCommand(Parser.extractArgs(commandArgs));
                break;
            case UNMARK:
                command = new UnmarkCommand(Parser.extractArgs(commandArgs));
                break;
            case LIST:
                command = new ListCommand();
                break;
            case FILTER:
                command = new FilterCommand(Parser.extractArgs(commandArgs));
                break;
            case FIND:
                return new FindCommand(Parser.extractArgs(commandArgs));
            case BYE:
                command = new ExitCommand();
                break;
            default:
                throw new InvalidCommandException();
            }

            return command;
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
