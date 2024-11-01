package darkpool.parser;

import static darkpool.parser.validator.ValidateDeadline.validate;
import static darkpool.parser.validator.ValidateDeadline.validateArrayLength;
import static darkpool.parser.validator.ValidateDeadline.validateBy;
import static darkpool.parser.validator.ValidateDeadline.validateEmptyDesc;

import darkpool.DarkpoolException;
import darkpool.command.AddCommand;
import darkpool.command.Command;
import darkpool.task.Deadline;

/**
 * DeadlineParser class is responsible for parsing user input for deadline tasks.
 */
public class DeadlineParser {

    /**
     * Parses user input for deadline tasks.
     *
     * @param userInput User input to be parsed.
     * @return Command object representing the deadline task.
     * @throws DarkpoolException If user input is invalid.
     */
    public static Command parse(String[] userInput) throws DarkpoolException {
        Deadline deadline = deadlineParse(userInput);
        return new AddCommand(deadline);
    }

    private static Deadline deadlineParse(String[] userInput) throws DarkpoolException {
        validate(userInput);
        String text = userInput[1];
        validateBy(text);

        String[] parts = splitInput(text);
        validateArrayLength(parts);

        String description = extractDescription(parts);
        String by = extractBy(parts);

        return new Deadline(description, by, false);
    }

    private static String[] splitInput(String input) {
        return input.split("/by");
    }

    private static String extractDescription(String[] parts) throws DarkpoolException {
        String description = parts[0].trim();
        validateEmptyDesc(description);
        return description;
    }

    private static String extractBy(String[] parts) {
        return parts[1].trim();
    }
}
