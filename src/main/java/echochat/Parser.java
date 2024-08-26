package main.java.echochat;

import main.java.Exceptions.EmptyDescriptionError;
import main.java.Exceptions.InvalidCommandError;

public class Parser {

    public Command parse(String input) throws EmptyDescriptionError, InvalidCommandError {
        String[] parts = input.split(" ", 2);

        if (input.equals("bye")) {
            return new Command(CommandType.EXIT, null, 0);
        } else if (input.equals("list")) {
            return new Command(CommandType.LIST, null, 0);
        } else if (parts.length == 2) {
            switch (parts[0]) {
                case "mark":
                    return new Command(CommandType.MARK, null, Integer.parseInt(parts[1]));
                case "unmark":
                    return new Command(CommandType.UNMARK, null, Integer.parseInt(parts[1]));
                case "delete":
                    return new Command(CommandType.DELETE, null, Integer.parseInt(parts[1]));
                case "todo":
                case "deadline":
                case "event":
                    return parseTask(parts[0], parts[1]);
                default:
                    throw new InvalidCommandError();
            }
        }
        throw new InvalidCommandError();
    }

    private Command parseTask(String type, String details) throws EmptyDescriptionError {
        String description = "";
        String by = null;
        String from = null;
        String to = null;

        if (details.contains(" /")) {
            String[] splitDetails = details.split(" /");
            description = splitDetails[0].trim();
            for (int i = 1; i < splitDetails.length; i++) {
                String detail = splitDetails[i].trim();
                if (detail.startsWith("by ")) {
                    by = detail.substring(3).trim();
                } else if (detail.startsWith("from ")) {
                    from = detail.substring(5);
                } else if (detail.startsWith("to ")) {
                    to = detail.substring(3).trim();
                }
            }
        } else {
            description = details;
        }

        if (description.isEmpty()) {
            throw new EmptyDescriptionError();
        }

        Task task = null;
        switch (type) {
            case "todo":
                task = new Todo(description);
                break;
            case "deadline":
                task = new Deadline(by, description);
                break;
            case "event":
                task = new Event(from, to, description);
                break;
            default:
                break;
        }
        return new Command(type.equals("todo") ? CommandType.TODO : type.equals("deadline") ? CommandType.DEADLINE : CommandType.EVENT, description, 0, task);
    }
}
