package darkpool.parser;

import darkpool.command.AddCommand;
import darkpool.command.Command;
import darkpool.task.Deadline;
import darkpool.DarkpoolException;

import static darkpool.parser.validator.ValidateDeadline.validate;
import static darkpool.parser.validator.ValidateDeadline.validateBy;
import static darkpool.parser.validator.ValidateDeadline.validateEmptyDesc;
import static darkpool.parser.validator.ValidateDeadline.validateArrayLength;

public class DeadlineParser {
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
