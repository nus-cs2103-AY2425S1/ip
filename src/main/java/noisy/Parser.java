package noisy;

import java.time.LocalDate;

/**
 * The Parser class is responsible for parsing user input and dates.
 * It provides methods for converting date strings to {@code LocalDate} objects
 * and for determining the type of command issued by the user.
 */
public class Parser {

    /**
     * Parses a date string into a {@code LocalDate} object.
     * The date string should be in the ISO-8601 format (e.g., "2024-09-06").
     *
     * @param date The date string to be parsed.
     * @return The {@code LocalDate} representation of the date string.
     * @throws java.time.format.DateTimeParseException If the date string is invalid.
     */
    public LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    public int parseInput(String input, TaskList taskList) {
        if (input.equals("bye")) {
            return 0;
        }

        if (input.equals("list")) {
            return 1;
        }

        if (input.startsWith("mark")) {
            return 2;
        }


        Task task = null;
        try {
            switch (input.split(" ")[0]) {
                case "todo":
                    if (input.split(" ", 2).length < 2) {
                        throw new NoisyException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    return 3;
                case "noisy.Deadline":
                    return 4;
                case "noisy.Event":
                    return 5;
                case "delete":
                    return 6;
                default:
                    throw new NoisyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (NoisyException e) {
            System.out.println(e);
        }
        return 7;
    }
}
