package utility;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.LukeException;

/** A class used for parsing command */
public class Parser {
    private Command command;
    private String description;
    private String by;
    private String from;
    private String to;
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
     *
     * @param line Is the command.
     * @throws LukeException When parsing an invalid command.
     */
    public void parse(String line) throws LukeException {
        String[] tokens = line.split(" ");
        getCommand(tokens);
        switch (command) {
        case list, bye -> { }
        case find -> this.description = tokens[1].trim();
        case note, todo -> description = line.substring(4).trim();
        case deleteNote, mark, unmark, delete -> parseIndex(tokens);
        case event -> parseEvent(line.substring(5));
        case deadline -> parseDeadLine(line.substring(8));
        default -> { }
        }
    }

    /**
     * Parse DeadLine task to retrieve description and by date.
     *
     * @param input Is the instruction without the command keyword.
     * @throws LukeException When instruction format is invalid.
     */
    private void parseDeadLine(String input) throws LukeException {
        int byIndex = getIndexOfToken(input, " /by ");
        description = parseDescription(input, byIndex);
        by = parseDate(input, byIndex + 4);
    }

    /**
     * Parse Event task to retrieve description, from and to dates.
     *
     * @param input Is the instruction without the command keyword.
     * @throws LukeException When instruction format is invalid.
     */
    private void parseEvent(String input) throws LukeException {
        int fromIndex = getIndexOfToken(input, " /from ");
        int toIndex = getIndexOfToken(input, " /to ");
        description = parseDescription(input, fromIndex);
        from = parseDate(input, fromIndex + 6, toIndex);
        to = parseDate(input,toIndex + 4);
    }

    private void getCommand(String[] tokens) throws LukeException {
        try {
            this.command = Command.valueOf(tokens[0].trim());
        } catch (IllegalArgumentException e) {
            throw new LukeException(String.format("Yo! This command \"%s\" doesn't exist.", tokens[0].trim()));
        }
    }

    private String parseDescription(String input, int beginIndex) {
        return input.substring(0, beginIndex).trim();
    }

    private void parseIndex(String[] tokens) throws LukeException {
        try {
            this.index = Integer.parseInt(tokens[1].trim());
        } catch (NumberFormatException e) {
            throw new LukeException("Index does not exists");
        }
    }

    private String parseDate(String input, int beginIndex) throws LukeException {
        try {
            return DateTime
                    .parseDate(input.substring(beginIndex).trim())
                    .format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new LukeException("Invalid Date format");
        }
    }

    private String parseDate(String input, int beginIndex, int endIndex) throws LukeException {
        try {
            return DateTime
                    .parseDate(input.substring(beginIndex, endIndex).trim())
                    .format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new LukeException("Invalid Date format");
        }
    }

    private int getIndexOfToken(String input, String token) throws LukeException {
        int index = input.indexOf(token);
        if (index == -1) {
            String trimmedToken = token.trim();
            if (input.contains(trimmedToken)) {
                throw new LukeException(String.format("There needs to be spacing between %s and other words.", trimmedToken));
            } else {
                throw new LukeException(String.format("Yo! A necessary %s is missing from your input.", trimmedToken));
            }
        }
        return index;
    }
}
