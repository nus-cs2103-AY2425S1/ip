package yapper.components;

import java.util.Random;

import yapper.exceptions.YapperException;
import yapper.exceptions.YapperFormatException;

/**
 * Parses user input from the command line.
 */
public class Parser {

    private static final String[] NO_USER_TEXT_RESPONSES = {
        "Huh?",
        "What?",
        "I didn't catch that.",
        "Speak up please.",
        "Uh?",
        "You there?",
        "..."
    };
    private Random random;
    private String rawInput;
    private String[] parsedLine;
    /**
     * Constructs a Parser that reads from the text box.
     */
    public Parser() {
        this.random = new Random();
        this.rawInput = "";
        this.parsedLine = new String[0];
    }

    /**
     * Parses the next line of input from the user.
     * Splits the line into words based on whitespace.
     *
     * @param input  a string to be split apart to identify the command
     * @throws YapperException if there is no input available or if parsing fails
     */
    public void parseLine(String input) throws YapperException {
        if (input.isEmpty()) {
            throw new YapperException(NO_USER_TEXT_RESPONSES[random.nextInt(NO_USER_TEXT_RESPONSES.length)]);
        }
        this.rawInput = input;
        this.parsedLine = input.split("\\s+");
    }

    public int getTokenCount() {
        return this.parsedLine.length;
    }

    public String getCommand() {
        assert this.parsedLine.length > 0 : "The parsed input should not give an empty list of strings.";
        return this.parsedLine[0];
    }

    public int getTaskNumber() throws YapperException {
        assert this.parsedLine.length > 1 : "The parsed input should contain a command and another argument.";
        try {
            return Integer.parseInt(this.parsedLine[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new YapperException("Format error: mark/unmark [TASK_NUMBER]");
        } catch (NumberFormatException e) {
            throw new YapperException("That was NOT a number.");
        }
    }

    public String getKeyword() throws YapperException {
        assert this.parsedLine.length > 1 : "The parsed input should contain a command and another argument";
        try {
            return this.parsedLine[1];
        } catch (IndexOutOfBoundsException e) {
            throw new YapperException("Format error: No keyword given");
        }
    }

    public String getToDoArguments() throws YapperFormatException {
        assert this.parsedLine.length > 1 : "The parsed input should contain a command and some arguments";
        try {
            return this.rawInput.split("todo")[1].trim();
        } catch (IndexOutOfBoundsException e) {
            throw new YapperFormatException("(Format: todo [DESC])");
        }
    }

    public String[] getDeadlineArguments() throws YapperException {
        assert this.parsedLine.length > 1 : "The parsed input should contain a command and some arguments";
        try {
            String[] deadline = this.rawInput.split("/by");
            String[] description = deadline[0].split("deadline");
            String[] deadlineArguments = {description[1].trim(), deadline[1].trim()};
            return deadlineArguments;
        } catch (IndexOutOfBoundsException e) {
            throw new YapperFormatException("(Format: deadline [DESC] /by [DEADLINE_BY])");
        }
    }

    public String[] getEventArguments() throws YapperException {
        assert this.parsedLine.length > 1 : "The parsed input should contain a command and some arguments";
        try {
            String[] toDate = this.rawInput.split("/to");
            String[] fromDate = toDate[0].split("/from");
            String[] description = fromDate[0].split("event");
            String[] eventArguments = {description[1].trim(), fromDate[1].trim(), toDate[1].trim()};
            return eventArguments;
        } catch (IndexOutOfBoundsException e) {
            throw new YapperFormatException("(Format: event [DESC] /from [FROM] /to [TO])");
        }
    }
}
