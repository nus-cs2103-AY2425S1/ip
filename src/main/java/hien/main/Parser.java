package hien.main;

import hien.command.*;
import hien.exception.HienException;
import hien.ui.UI;


class Parser {
    private UI ui;

    public static Command parse(String input) throws HienException {
        String[] parts = input.split(" ", 2);
        String commandType = parts[0].toLowerCase();
        String rest = parts.length > 1 ? parts[1] : "";
        switch (commandType) {
            case "list":
                return new ListCommand(false);
            case "mark":
                return new MarkCommand(input, true, false);
            case "unmark":
                return new MarkCommand(input, false, false);
            case "todo":
                return new TodoCommand(input, false);
            case "deadline":
                // Parse deadline details and create Deadline object
                return new DeadlineCommand(input, false);
            case "event":
                // Parse event details and create Event object
                return new EventCommand(input, false);
            case "delete":
                return new DeleteCommand(input, false);
            case "bye":
                return new ExitCommand(true);
            default:
                throw new HienException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}