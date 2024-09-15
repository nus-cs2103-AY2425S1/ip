package darkpool.parser.validator;

import darkpool.DarkpoolException;
import darkpool.task.Event;

import java.time.format.DateTimeParseException;
import java.util.Objects;

public class ValidateEvent {
    public static void validate(String[] userInput) throws DarkpoolException {
        if (userInput.length < 2 || Objects.equals(userInput[1], "")) {
            throw new DarkpoolException("the event syntax is [task_desc] /from [from_date] /to [to_date]");
        }
    }

    public static void validateTo(String[] toParts) throws DarkpoolException {
        if (toParts.length > 3) {
            throw new DarkpoolException("only one to pls");
        } else if (toParts.length < 2 || toParts[1].trim().isEmpty()) {
            throw new DarkpoolException("to where???");
        } else if (toParts[0].trim().isEmpty()) {
            throw new DarkpoolException("from where???");
        }
    }

    public static void validateFrom(String[] fromParts) throws DarkpoolException {
        if (fromParts.length > 2) {
            throw new DarkpoolException("only one from pls");
        } else if (fromParts.length < 2 || fromParts[1].trim().isEmpty()) {
            throw new DarkpoolException("where is from bruh");
        } else if (fromParts[0].trim().isEmpty()) {
            throw new DarkpoolException("task where???");
        }
    }

    public static Event validateAndCreateEvent(String desc, String from, String to) throws DarkpoolException {
        try {
            return new Event(desc, from.trim(), to.trim(), false);
        } catch (DateTimeParseException e) {
            throw new DarkpoolException("bro you know what a date time format is?");
        }
    }
}
