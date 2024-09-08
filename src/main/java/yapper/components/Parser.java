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

    /**
     * Gets the total number of words in the input
     * @return number of words in input
     */
    public int getTokenCount() {
        return this.parsedLine.length;
    }

    /**
     * Gets the user command which is designated as the first word of the input
     * @return a string representing the command
     */
    public String getCommand() {
        assert this.parsedLine.length > 0 : "The parsed input should not give an empty list of strings.";
        return this.parsedLine[0];
    }

    /**
     * Gets the task number
     * @return                 an int representing the task number (1-indexed)
     * @throws YapperException if the string being parsed is not amn integer
     */
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

    /**
     * Gets the keyword used for the findTask method in TaskList
     * @return                 a String representing the keyword
     * @throws YapperException if theres no keyword found in the input
     */
    public String getKeyword() throws YapperException {
        assert this.parsedLine.length > 1 : "The parsed input should contain a command and another argument";
        try {
            return this.parsedLine[1];
        } catch (IndexOutOfBoundsException e) {
            throw new YapperException("Format error: No keyword given");
        }
    }

    /**
     * Returns the alias and command to alias
     * @return                       a string array containing the alias and command to alias;
     * @throws YapperFormatException if parsed input does not meet the required length
     */
    public String getAliasToUnbind() throws YapperFormatException {
        assert this.parsedLine.length >= 2 : "Parsed input should have set alias command, alias and existing command";
        if (this.parsedLine.length < 2) {
            throw new YapperFormatException("Format error: unbind [ALIAS]");
        }
        return this.parsedLine[1];
    }

    /**
     * Returns the alias and command to alias
     * @return                       a string array containing the alias and command to alias;
     * @throws YapperFormatException if parsed input does not meet the required length
     */
    public String[] getAliasAndCommand() throws YapperFormatException {
        assert this.parsedLine.length >= 3 : "Parsed input should have set alias command, alias and existing command";
        if (this.parsedLine.length < 3) {
            throw new YapperFormatException("Format error: bind [ALIAS] [COMMAND_TO_ALIAS]");
        }
        String alias = this.parsedLine[1];
        String commandToAlias = this.parsedLine[2];
        return new String[] { alias, commandToAlias };
    }

    /**
     * Returns the arguments required for instantiating a ToDo object
     * @return                       a task description in the form of a String
     * @throws YapperFormatException if the description is empty
     */
    public String getToDoArguments() throws YapperFormatException {
        assert this.parsedLine.length > 1 : "The parsed input should contain a command and some arguments";
        assert getCommand().equals("todo") || getCommand().equals("t") || getCommand().equals("T");
        String description = this.rawInput.substring(getCommand().length()).trim();
        if (description.isEmpty()) {
            throw new YapperFormatException("(Format: todo/t/T [DESC]");
        }
        return description;
    }

    /**
     * Returns the arguments required for instantiating a Deadline object
     * @return                       a task description and deadline in a String array
     * @throws YapperFormatException if the description or deadline is empty
     */
    public String[] getDeadlineArguments() throws YapperException {
        assert this.parsedLine.length > 1 : "The parsed input should contain a command and some arguments";
        assert getCommand().equals("deadline") || getCommand().equals("d") || getCommand().equals("D");
        try {
            String[] descriptionAndDeadline = this.rawInput.split("/by");
            // Organise description and deadline
            String description = descriptionAndDeadline[0].substring(getCommand().length()).trim();
            String deadline = descriptionAndDeadline[1].trim();
            // Check for any empty values
            if (description.isEmpty() || deadline.isEmpty()) {
                throw new YapperFormatException("(Format: deadline [DESC] /by [DEADLINE_BY])");
            }
            String[] deadlineArguments = {description, deadline};
            return deadlineArguments;
        } catch (IndexOutOfBoundsException e) {
            throw new YapperFormatException("(Format: deadline [DESC] /by [DEADLINE_BY])");
        }
    }

    /**
     * Returns the arguments required for instantiating an Event object
     * @return                       a task description, from date and to date in a String array
     * @throws YapperFormatException if the description, from date or to date are empty
     */
    public String[] getEventArguments() throws YapperException {
        assert this.parsedLine.length > 1 : "The parsed input should contain a command and some arguments";
        assert getCommand().equals("event") || getCommand().equals("e") || getCommand().equals("E");
        try {
            String[] otherArgumentsAndToDate = this.rawInput.split("/to");
            String[] descriptionAndFromDate = otherArgumentsAndToDate[0].split("/from");
            // Organise description, from date, to date
            String description = descriptionAndFromDate[0].substring(getCommand().length()).trim();
            String fromDate = descriptionAndFromDate[1].trim();
            String toDate = otherArgumentsAndToDate[1].trim();
            // Check for any empty values
            if (description.isEmpty() || fromDate.isEmpty() || toDate.isEmpty()) {
                throw new YapperFormatException("(Format: event [DESC] /from [FROM] /to [TO])");
            }
            String[] eventArguments = {description, fromDate, toDate};
            return eventArguments;
        } catch (IndexOutOfBoundsException e) {
            throw new YapperFormatException("(Format: event [DESC] /from [FROM] /to [TO])");
        }
    }
}
