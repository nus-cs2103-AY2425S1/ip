package myapp.helperbuddy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Parses a command string to create an appropriate Task object
     * If the command format is incorrect or any part of the command is invalid,
     * an error message is printed and no Task object is returned
     *
     * @param command user's command string to be parsed
     * @return Task object depending on the user's command
     */
    public static Task parseCommand(String command) {
        if (command.startsWith("todo")) {
            String description = command.substring(5).trim();
            if (description.isEmpty()) {
                System.out.println("________________________________________________");
                System.out.println("Sorry! The description cannot be empty.");
                System.out.println("________________________________________________");
            } else {
                return new ToDo(description);
            }
        } else if (command.startsWith("deadline")) {
            try {
                String[] parts = command.split(" /by ");
                String description = parts[0].substring(9).trim();
                LocalDateTime deadline = parseDateTime(parts[1].trim());
                if (description.isEmpty() || deadline == null) {
                    System.out.println("________________________________________________");
                    System.out.println("Sorry! The description cannot be empty");
                    System.out.println("The deadline timing should be in dd/MM/yyyy HHmm format.");
                    System.out.println("________________________________________________");
                } else {
                    return new Deadline(description, deadline);
                }
            } catch (Exception e) {
                System.out.println("Error parsing deadline command.");
                return null;
            }
        } else if (command.startsWith("event")) {
            try {
                String[] parts = command.split(" /from ");
                String[] subParts = parts[1].split(" /to ");
                String description = parts[0].substring(6).trim();
                LocalDateTime from = parseDateTime(subParts[0].trim());
                LocalDateTime to = parseDateTime(subParts[1].trim());
                if (description.isEmpty() || from == null || to == null) {
                    System.out.println("________________________________________________");
                    System.out.println("Sorry! The description cannot be empty");
                    System.out.println("The from and to timings should be in dd/MM/yyyy HHmm format.");
                    System.out.println("________________________________________________");
                } else {
                    return new Event(description, from, to);
                }
            } catch (Exception e) {
                System.out.println("Error parsing event command.");
                return null;
            }
        } else {
            System.out.println("________________________________________________");
            System.out.println("Invalid command. Please use 'todo', 'deadline', 'event', 'delete'," +
                    " 'mark', 'unmark', 'list' or 'bye'. Thank you for understanding!");
            System.out.println("________________________________________________");
        }
        return null;
    }

    /**
     * Parses a command string to create an appropriate datetime object
     * If the command format is incorrect or any part of the command is invalid,
     * an error message is printed and no LocalDateTime object is returned
     *
     * @param dateTimeStr user's command string to be parsed
     * @return LocalDateTime object depending on the user's command
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date/time: " + dateTimeStr);
            return null;
        }
    }
}
