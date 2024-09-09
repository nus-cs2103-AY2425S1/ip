package yihuibot.ui;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import yihuibot.exception.parse.CommandNotFoundException;
import yihuibot.exception.parse.ParseException;
import yihuibot.executable.Executable;
import yihuibot.ui.function.ByeFunction;
import yihuibot.ui.function.DeadlineFunction;
import yihuibot.ui.function.DeleteFunction;
import yihuibot.ui.function.EventFunction;
import yihuibot.ui.function.FindFunction;
import yihuibot.ui.function.ListFunction;
import yihuibot.ui.function.MarkFunction;
import yihuibot.ui.function.TodoFunction;
import yihuibot.ui.function.UnmarkFunction;

/**
 * A parser to parse user's input from YihuiBot, returning the appropriate executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Parser {
    private String dateTimeFormat;
    private DateTimeFormatter formatter;

    /**
     * Constructor for a new Parser. Takes in a string pattern for formatting
     * date time strings.
     *
     * @param dateTimeFormat the date time format pattern.
     * @throws IllegalArgumentException if the pattern is not valid.
     */
    public Parser(String dateTimeFormat) throws IllegalArgumentException {
        this.dateTimeFormat = dateTimeFormat;
        formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
    }

    /**
     * Parse the user's input, breaking it down into a command and an array of
     * arguments. Based on what command and arguments were given, the Parser will
     * instantiate the appropriate executable with the appropriate arguments. This
     * executable can then be separately executed to get its output.
     *
     * @param input the user's input.
     * @return the appropriate executable instantiated with the arguments.
     * @throws NullPointerException when input is null.
     * @throws ParseException when error occurred during parsing (e.g. incorrect arguments).
     */
    public Executable parse(String input) throws NullPointerException, ParseException {
        if (input == null) {
            throw new NullPointerException("User's input cannot be null.");
        }

        String[] inputArray = input.split(" ");
        String command = inputArray.length < 1 ? "" : inputArray[0];
        String[] arguments = inputArray.length < 2
                ? null
                : Arrays.copyOfRange(inputArray, 1, inputArray.length);

        switch (command) {
        case "bye":
            return new ByeFunction().call(arguments);
        case "list":
            return new ListFunction().call(arguments);
        case "mark":
            return new MarkFunction().call(arguments);
        case "unmark":
            return new UnmarkFunction().call(arguments);
        case "todo":
            return new TodoFunction().call(arguments);
        case "deadline":
            return new DeadlineFunction(dateTimeFormat, formatter).call(arguments);
        case "event":
            return new EventFunction(dateTimeFormat, formatter).call(arguments);
        case "delete":
            return new DeleteFunction().call(arguments);
        case "find":
            return new FindFunction().call(arguments);
        default:
            throw new CommandNotFoundException(input);
        }
    }
}
