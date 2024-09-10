package vuewee.parser;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command option with its associated description and parser.
 * Options are passed as strings in the format "/option value" where "option" is
 * the option string and "value" is the option value. The option value is parsed
 * using the specified parser function and saved in the parsedValue field.
 *
 * @param <T> the type of the parsed value
 */
public class CommandOption<T> {
    private final String option;
    private final String description;
    private final Function<String, T> parser;
    private T parsedValue;

    /**
     * Constructs a CommandOption object with the specified option, description, and
     * parser. The parser function is used to convert the option value to the
     * desired type and perform validation checks as appropriate.
     *
     * @param option      the option string
     * @param description the description of the option
     * @param parser      the parser function to convert the option value to the
     *                    desired type
     */
    public CommandOption(String option, String description, Function<String, T> parser) {
        this.option = option;
        this.description = description;
        this.parser = parser;
    }

    /**
     * Creates a CommandOption object for a string option with the specified option
     * and description.
     *
     * @param option      the option string
     * @param description the description of the option
     * @return a CommandOption object for a string option
     */
    public static CommandOption<String> stringOption(String option, String description) {
        return new CommandOption<>(option, description, Function.identity());
    }

    /**
     * Returns the option string.
     *
     * @return the option string
     */
    public String getOption() {
        return this.option;
    }

    /**
     * Returns the description of the option.
     *
     * @return the description of the option
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the parsed value of the option.
     *
     * @return the parsed value of the option
     */
    public T getParsedValue() {
        return this.parsedValue;
    }

    /**
     * Parses the input string for the option value and saves the parsed value.
     *
     * @param input        the input string to parse
     * @param endDelimeter the delimiter marking the end of the option value
     * @return the index of the start of the match
     * @throws IllegalArgumentException if the option is not found in the input
     *                                  string
     */
    public int parse(String input, String endDelimeter) throws IllegalArgumentException {
        // Match option format: /option1 value1 /option2 value2 ...
        Pattern pattern = Pattern.compile("/" + this.option + " (.+?)" + endDelimeter);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException();
        }
        this.parsedValue = this.parser.apply(matcher.group(1));
        return matcher.start();
    }
}
