package utility;

import exceptions.LukeException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public Command command;
    public String description, by, from, to = null;
    public int index;

    public Parser() {

    }

    public void parse(String line) throws LukeException {
        String[] parameters = line.split(" ");
        try {
            command = Command.valueOf(parameters[0].trim());
        } catch (IllegalArgumentException e) {
            throw new LukeException(String.format("Yo! This command \"%s\" doesn't exist.", parameters[0].trim()));
        }
        switch (command) {
        case list -> {}
        case find -> description = line.substring(4).trim();
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
        case bye -> {}
        }
    }

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
