package darkpool.parser;

import darkpool.command.AddCommand;
import darkpool.command.Command;
import darkpool.task.After;
import darkpool.DarkpoolException;

import static darkpool.parser.validator.ValidateAfter.validate;
import static darkpool.parser.validator.ValidateAfter.validateFrom;
import static darkpool.parser.validator.ValidateAfter.validateEmptyDesc;
import static darkpool.parser.validator.ValidateAfter.validateArrayLength;

/**
 * Parses the user input for the 'after' command.
 */
public class AfterParser {

    /**
     * Parses the user input for the 'after' command.
     *
     * @param userInput The user input to be parsed.
     * @return The 'after' command.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static Command parse(String[] userInput) throws DarkpoolException {
        After after = afterParse(userInput);
        return new AddCommand(after);
    }

    /**
     * Parses the user input for the 'after' command.
     *
     * @param userInput The user input to be parsed.
     * @return The 'after' task.
     * @throws DarkpoolException If the user input is invalid.
     */
    private static After afterParse(String[] userInput) throws DarkpoolException {
        validate(userInput);
        String text = userInput[1];
        validateFrom(text);

        String[] parts = splitInput(text);
        validateArrayLength(parts);

        String description = extractDescription(parts);
        String from = extractFrom(parts);

        return new After(description, from, false);
    }

    /**
     * Splits the user input into description and 'from' parts.
     *
     * @param input The user input to be split.
     * @return The description and 'from' parts.
     */
    private static String[] splitInput(String input) {
        return input.split("/from");
    }

    /**
     * Extracts the description from the user input.
     *
     * @param parts The description and 'from' parts.
     * @return The description.
     * @throws DarkpoolException If the description is empty.
     */
    private static String extractDescription(String[] parts) throws DarkpoolException {
        String description = parts[0].trim();
        validateEmptyDesc(description);
        return description;
    }

    /**
     * Extracts the 'from' part from the user input.
     *
     * @param parts The description and 'from' parts.
     * @return The 'from' part.
     */
    private static String extractFrom(String[] parts) {
        return parts[1].trim();
    }
}
