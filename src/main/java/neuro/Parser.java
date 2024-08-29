package neuro;

import neuro.command.AddCommand;
import neuro.command.Command;
import neuro.command.ExitCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String userCommand) {
        if (userCommand.equals("bye")) {
            return new ExitCommand();
        } else if (userCommand.equals("list")) {
            return null;
        } else if (userCommand.startsWith("mark")) {
            return null;
        } else if (userCommand.startsWith("unmark")) {
            return null;
        } else if (userCommand.startsWith("delete")) {
            return null;
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
}
