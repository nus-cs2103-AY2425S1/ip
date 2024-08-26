package utility;

import exceptions.LukeException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** A class used for parsing command */
public class Parser {
    private Command command;
    private String description, by, from, to;
    private int index;

    /**
     * Returns an instance of Parser object.
     */
    public Parser() {
    }

    public Command getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public String getBy() {
        return this.by;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getIndex() {
        return index;
    }

    /**
     * Parses the command given and updates the class variable.
     * @param line Is the command.
     * @throws LukeException When parsing an invalid command.
     */
    public void parse(String line) throws LukeException {
        String[] parameters = line.split(" ");
        try {
            command = Command.valueOf(parameters[0].trim());
        } catch (IllegalArgumentException e) {
            throw new LukeException(String.format("Yo! This command \"%s\" doesn't exist.", parameters[0].trim()));
        }
        switch (command) {
        case list, bye -> {}
        case mark, unmark, delete -> {
            try {
                index = Integer.parseInt(parameters[1].trim());
            } catch (NumberFormatException e) {
                throw new LukeException("Index does not exists");
            }

        }
        case todo -> description = line.substring(4).trim();
        case event -> parseEvent(line.substring(5));
        case deadline -> parseDeadLine(line.substring(8));
        }
    }

    /**
     * Parse DeadLine task to retrieve description and by date.
     * @param input Is the instruction without the command keyword.
     * @throws LukeException When instruction format is invalid.
     */
    public void parseDeadLine(String input) throws LukeException {
        int slashIndex = input.indexOf(" /by ");
        if (slashIndex == -1) {
            if (input.contains("/by")) {
                throw new LukeException("There needs to be spacing between /by and other words.");
            } else {
                throw new LukeException("Missing /by to indicate when the deadline of the task.");
            }
        }
        description = input.substring(0, slashIndex).trim();
        String dateString = input.substring(slashIndex + 4).trim();
        try {
            by = DateTime.parseDate(dateString).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            by = "";
            throw new LukeException("Invalid Date format");
        }
    }

    /**
     * Parse Event task to retrieve description, from and to dates.
     * @param input Is the instruction without the command keyword.
     * @throws LukeException When instruction format is invalid.
     */
    public void parseEvent(String input) throws LukeException {
        int firstSlashIndex = input.indexOf(" /from ");
        if (firstSlashIndex == -1) {
            if (input.contains("/from")) {
                throw new LukeException("There needs to be spacing between /from and other words.");
            } else {
                throw new LukeException("Missing /from to indicate the start time of the event.");
            }
        }
        int secondSlashIndex = input.indexOf(" /to ");
        if (secondSlashIndex == -1) {
            if (input.contains("/to")) {
                throw new LukeException("There needs to be spacing between /to and other words.");
            } else {
                throw new LukeException("Missing /to to indicate the end time of the event.");
            }
        }
        description = input.substring(0, firstSlashIndex).trim();
        try {
            from = DateTime.parseDate(input.substring(firstSlashIndex + 6, secondSlashIndex).trim()).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            to = DateTime.parseDate(input.substring(secondSlashIndex + 4).trim()).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            from = "";
            to = "";
            throw new LukeException("Invalid Date format");
        }
    }

}
