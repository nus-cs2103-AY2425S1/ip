package darkpool.parser;

import darkpool.command.AddCommand;
import darkpool.command.Command;
import darkpool.task.After;
import darkpool.DarkpoolException;

import static darkpool.parser.validator.ValidateAfter.validate;
import static darkpool.parser.validator.ValidateAfter.validateFrom;
import static darkpool.parser.validator.ValidateAfter.validateEmptyDesc;
import static darkpool.parser.validator.ValidateAfter.validateArrayLength;

public class AfterParser {
    public static Command parse(String[] userInput) throws DarkpoolException {
        After after = afterParse(userInput);
        return new AddCommand(after);
    }

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

    private static String[] splitInput(String input) {
        return input.split("/from");
    }

    private static String extractDescription(String[] parts) throws DarkpoolException {
        String description = parts[0].trim();
        validateEmptyDesc(description);
        return description;
    }

    private static String extractFrom(String[] parts) {
        return parts[1].trim();
    }
}
