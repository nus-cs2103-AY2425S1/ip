package darkpool.parser;

import static darkpool.parser.validator.ValidateEvent.validate;
import static darkpool.parser.validator.ValidateEvent.validateAndCreateEvent;
import static darkpool.parser.validator.ValidateEvent.validateFrom;
import static darkpool.parser.validator.ValidateEvent.validateTo;

import darkpool.DarkpoolException;
import darkpool.command.AddCommand;
import darkpool.command.Command;
import darkpool.task.Event;

/**
 * Parses user input to create an Event object.
 */
public class EventParser {

    /**
     * Parses user input to create an Event object.
     *
     * @param userInput User input.
     * @return Command object representing the user input.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static Command parse(String[] userInput) throws DarkpoolException {
        Event event = parseEvent(userInput);
        return new AddCommand(event);
    }

    private static Event parseEvent(String[] userInput) throws DarkpoolException {
        validate(userInput);
        String eventString = userInput[1];

        String description = extractDescription(eventString);
        String from = extractFrom(eventString);
        String to = extractTo(eventString);

        return validateAndCreateEvent(description, from, to);
    }

    private static String extractDescription(String eventString) throws DarkpoolException {
        String[] parts = eventString.split("/from");
        validateFrom(parts);
        return parts[0].trim();
    }

    private static String extractFrom(String eventString) throws DarkpoolException {
        String[] fromParts = eventString.split("/from");
        validateFrom(fromParts);
        String[] toParts = fromParts[1].split("/to ");
        validateTo(toParts);
        return toParts[0].trim();
    }

    private static String extractTo(String eventString) throws DarkpoolException {
        String[] parts = eventString.split("/to ");
        validateTo(parts);
        return parts[parts.length - 1].trim();
    }
}
