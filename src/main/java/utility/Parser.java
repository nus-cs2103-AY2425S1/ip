package utility;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.LukeException;

/**
 * A class used for parsing command
 */
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
     * @param line Command from user.
     * @throws LukeException When parsing an invalid command.
     */
    public void parse(String line) throws LukeException {
        String[] tokens = line.split(" ");
        parseCommand(tokens);
        switch (command) {
        case find -> this.description = tokens[1].trim();
        case note, todo -> description = line.substring(4).trim();
        case mark, unmark, delete -> parseIndex(tokens);
        case event -> parseEvent(line.substring(5));
        case deadline -> parseDeadLine(line.substring(8));
        default -> { }
        }
    }

    /**
     * Parses DeadLine task to retrieve description and by date.
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
     * Parses Event task to retrieve description, from and to dates.
     *
     * @param input Is the instruction without the command keyword.
     * @throws LukeException When instruction format is invalid.
     */
    private void parseEvent(String input) throws LukeException {
        int fromIndex = getIndexOfToken(input, " /from ");
        int toIndex = getIndexOfToken(input, " /to ");
        description = parseDescription(input, fromIndex);
        from = parseDate(input, fromIndex + 6, toIndex);
        to = parseDate(input, toIndex + 4);
    }

    /**
     * Parses the command keyword from user command.
     *
     * @param tokens User command split by space.
     */
    private void parseCommand(String[] tokens) {
        try {
            this.command = Command.valueOf(tokens[0].trim());
        } catch (IllegalArgumentException e) {
            this.command = Command.parseError;
        }
    }

    /**
     * Returns the description from user command.
     *
     * @param input The user command.
     * @param endIndex The end index of the description.
     * @return The description from user command.
     */
    private String parseDescription(String input, int endIndex) {
        return input.substring(0, endIndex).trim();
    }

    /**
     * Parses the user input index.
     *
     * @param tokens User command split by space.
     * @throws LukeException When parsing the index fails.
     */
    private void parseIndex(String[] tokens) throws LukeException {
        try {
            this.index = Integer.parseInt(tokens[1].trim());
        } catch (NumberFormatException e) {
            throw new LukeException("Index does not exists");
        }
    }

    /**
     * Returns a String representation of user input date in MMM dd yyyy format.
     *
     * @param input User command.
     * @param beginIndex Beginning index of date.
     * @return LocalDate in MMM dd yyyy date format in String.
     * @throws LukeException When parsing date fails.
     */
    private String parseDate(String input, int beginIndex) throws LukeException {
        try {
            return DateTime
                    .parseDate(input.substring(beginIndex).trim())
                    .format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new LukeException("Invalid Date, must be of format: yyyy-MM-dd and valid date");
        }
    }

    /**
     * Returns a String representation of user input date in MMM dd yyyy format.
     *
     * @param input User command.
     * @param beginIndex Beginning index of date.
     * @param endIndex End index of date.
     * @return LocalDate in MMM dd yyyy date format in String.
     * @throws LukeException When parsing date fails.
     */
    private String parseDate(String input, int beginIndex, int endIndex) throws LukeException {
        try {
            return DateTime
                    .parseDate(input.substring(beginIndex, endIndex).trim())
                    .format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new LukeException("Invalid Date, must be of format: yyyy-MM-dd and valid date");
        }
    }

    /**
     * Returns an index of the start of token.
     *
     * @param input User command.
     * @param token Token to find in user command.
     * @return An index of the start of token.
     * @throws LukeException When token is missing or malformed.
     */
    private int getIndexOfToken(String input, String token) throws LukeException {
        int index = input.indexOf(token);
        if (index == -1) {
            String trimmedToken = token.trim();
            if (input.contains(trimmedToken)) {
                throw new LukeException(
                        String.format("There needs to be spacing between %s and other words.", trimmedToken));
            } else {
                throw new LukeException(
                        String.format("Yo! A necessary %s is missing from your input.", trimmedToken));
            }
        }
        return index;
    }
}
