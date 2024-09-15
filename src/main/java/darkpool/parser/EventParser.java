package darkpool.parser;

import darkpool.command.AddCommand;
import darkpool.command.Command;
import darkpool.task.Event;
import darkpool.DarkpoolException;

import static darkpool.parser.validator.ValidateEvent.validate;
import static darkpool.parser.validator.ValidateEvent.validateFrom;
import static darkpool.parser.validator.ValidateEvent.validateTo;
import static darkpool.parser.validator.ValidateEvent.validateAndCreateEvent;

public class EventParser {
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