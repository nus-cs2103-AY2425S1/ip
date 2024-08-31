package Ponder_Pika.Parser;

import Ponder_Pika.Command;
import Ponder_Pika.Exception.PonderPikaException;
import Ponder_Pika.Task.Deadline;
import Ponder_Pika.Task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");

    /**
     * Parses the user command and returns a Command object.
     *
     * @param command The user input command string.
     * @return A Command object representing the parsed command.
     * @throws PonderPikaException If there is an error in parsing the command.
     */
    public Command parseCommand(String command) throws PonderPikaException {
        String[] commands = command.split(" ", 2);
        String action = commands[0].toLowerCase();

        switch (action) {
        case "list":
            return new Command(Command.Action.LIST, null);

        case "mark":
            return new Command(Command.Action.MARK, Integer.parseInt(commands[1]));

        case "unmark":
            return new Command(Command.Action.UNMARK, Integer.parseInt(commands[1]));

        case "todo":
            if (commands.length < 2) {
                throw new PonderPikaException("Missing Description for Todo Task!");
            }
            return new Command(Command.Action.TODO, commands[1]);

        case "deadline":
            return parseDeadlineCommand(commands[1]);

        case "event":
            return parseEventCommand(commands[1]);

        case "delete":
            return new Command(Command.Action.DELETE, Integer.parseInt(commands[1]));

        case "find":
            if (commands.length < 2) throw new PonderPikaException("Missing keyword for find command!");
            return new Command(Command.Action.FIND, commands[1].trim());

        case "bye":
            return new Command(Command.Action.BYE, null);

        default:
            throw new PonderPikaException("Invalid Command, Please Try Again!");
        }
    }

    private Command parseDeadlineCommand(String details) throws PonderPikaException {
        String[] args = details.split("/by ");
        if (args.length < 2) {
            throw new PonderPikaException("Missing Deadline time for Deadline Task!");
        }
        if (args[0].trim().isEmpty()) {
            throw new PonderPikaException("Missing Description for Deadline Task!");
        }
        return new Command(Command.Action.DEADLINE, new Deadline(args[0].trim(),
                LocalDateTime.parse(args[1].trim(), FORMATTER)));
    }


    private Command parseEventCommand(String details) throws PonderPikaException {
        String[] desc = details.split("/from");
        if (desc.length < 2) {
            throw new PonderPikaException("Missing \"From\" and \"To\" times for Event Task!");
        }
        if (desc[0].trim().isEmpty()) {
            throw new PonderPikaException("Missing Description for Event Task!");
        }

        String[] time = desc[1].split("/to");
        if (time.length < 2) {
            throw new PonderPikaException("Missing \"To\" timeline for Event Task!");
        }
        if (time[0].trim().isEmpty()) {
            throw new PonderPikaException("Missing \"From\" timeline for Event Task!");
        }

        return new Command(Command.Action.EVENT, new Event(desc[0].trim(),
                LocalDateTime.parse(time[0].trim(), FORMATTER), LocalDateTime.parse(time[1].trim(), FORMATTER)));
    }
}
