package neuro;

import neuro.command.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String userCommand) {
        if (userCommand.equals("bye")) {
            return new ExitCommand();
        } else if (userCommand.equals("list")) {
            return new ListCommand();
        } else if (userCommand.startsWith("mark")) {
            return new MarkCommand(getIndexFromCommand(userCommand));
        } else if (userCommand.startsWith("unmark")) {
            return new UnmarkCommand(getIndexFromCommand(userCommand));
        } else if (userCommand.startsWith("delete")) {
            return new DeleteCommand(getIndexFromCommand(userCommand));
        } else if (userCommand.startsWith("find")) {
            return new FindCommand(userCommand.substring("find".length()));
        } else {
            return new AddCommand(userCommand.split(" "));
        }
    }

    public static LocalDateTime parseDateTime(String dateTimeStr) throws IllegalArgumentException {
        DateTimeFormatter[] dateTimeFormatters = {
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-M-d HHmm"),
                DateTimeFormatter.ofPattern("MMM d yyyy h a"),
                DateTimeFormatter.ofPattern("MMM d yyyy ha")
        };

        DateTimeFormatter[] dateFormatters = {
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("yyyy-M-d"),
                DateTimeFormatter.ofPattern("MMM d yyyy")
        };

        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(dateTimeStr, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                return LocalDate.parse(dateTimeStr, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        throw new IllegalArgumentException("Invalid date string!");
    }

    public static int getDeadlineByIndex(String[] commandComponents) {
        int byIndex = -1;
        for (int i = 1; i < commandComponents.length; i++) {
            if (commandComponents[i].equals("/by")) {
                byIndex = i;
                break;
            }
        }

        // Missing /by
        if (byIndex < 0) {
            throw new IllegalArgumentException("UH OH! The command given is missing the '/by' input for deadline." +
                    "Try updating the command like 'deadline finish homework /by Mon 2359'.");
        }
        return byIndex;
    }

    public static int getEventFromIndex(String[] commandComponents) {
        int fromIndex = -1;
        for (int i = 1; i < commandComponents.length; i++) {
            if (commandComponents[i].equals("/from")) {
                fromIndex = i;
                break;
            }
        }

        // Missing /from
        if (fromIndex < 0) {
            throw new IllegalArgumentException("UH OH! The command given is missing the '/from' input for event." +
                    "Try updating the command like 'event project meeting /from Mon 2pm /to 5pm'.");
        }
        return fromIndex;
    }

    public static int getEventToIndex(String[] commandComponents) {
        int toIndex = -1;
        for (int i = 1; i < commandComponents.length; i++) {
            if (commandComponents[i].equals("/to")) {
                toIndex = i;
                break;
            }
        }

        // Missing /to
        if (toIndex < 0) {
            throw new IllegalArgumentException("UH OH! The command given is missing the '/to' input for event." +
                    "Try updating the command like 'event project meeting /from Mon 2pm /to 5pm'.");
        }
        return toIndex;
    }

    private static int getIndexFromCommand(String userCommand) {
        String[] commandComponents = userCommand.split(" ");

        try {
            return Integer.valueOf(commandComponents[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return -1;
        }
    }
}
