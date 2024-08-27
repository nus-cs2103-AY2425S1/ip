package beeboo.components;

import beeboo.command.*;
import beeboo.exception.InvalidCommandException;

public class Parser {

    public static Command parseCommand(String command) throws InvalidCommandException {
        command = command.trim();
            if (command.equals("list")) {
                return new ListCommand();
            }
            if (command.equals("bye")) {
                return new ExitCommand();
            }
            int index = command.indexOf(' ');
            String commandWord = command.substring(0, index).trim();
            String rest = command.substring(index).trim();
            switch (commandWord) {
            case "deadline":
                return new AddCommand("d", rest);
            case "todo":
                return new AddCommand("t", rest);
            case "event":
                return new AddCommand("e", rest);
            case "mark":
                return new MarkCommand("m", rest);
            case "unmark":
                return new MarkCommand("u", rest);
            case "delete":
                return new DeleteCommand(rest);
            default:
                throw new InvalidCommandException();
            }
        }
    }
