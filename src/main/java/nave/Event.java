package nave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code Event} class represents a task with a start and end date.
 * <p>
 * It extends the {@code Task} class to add functionality for handling tasks that have a specific time frame.
 * This includes parsing input to create an {@code Event} instance, formatting the task for display, and
 * converting it to a file-compatible format.
 * </p>
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs an {@code Event} task with the specified name, start date, and end date.
     *
     * @param name the name of the event task
     * @param startDate the start date of the event
     * @param endDate the end date of the event
     */
    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates a new {@code Event} instance based on the provided input string.
     * <p>
     * The method uses regular expressions to validate the input and ensure it contains a valid task name as well
     * as properly formatted start and end dates. If the input does not match the expected pattern, appropriate
     * {@code WrongInputException}s are thrown based on the nature of the input error.
     * </p>
     *
     * @param input the input string containing the task name, start date, and end date
     * @return an {@code Event} object if the input is valid
     * @throws WrongInputException if the input contains errors such as missing details or incorrect date format
     */
    public static Event handleInput(String input) throws WrongInputException {
        Pattern correctPattern =
                Pattern.compile("((\\w+\\s*)+) /from (\\d{4}-\\d{2}-\\d{2}) /to (\\d{4}-\\d{2}-\\d{2})");
        Matcher correctMatcher = correctPattern.matcher(input);

        Pattern wrongPattern1 = Pattern.compile(
                "(\\s*|\\s*/from|\\s*/to|\\s*/from.*/to ((.+\\s*)*))");
        Matcher wrongMatcher1 = wrongPattern1.matcher(input);

        Pattern wrongPattern2 = Pattern.compile("((.+\\s*)*)( /from)?( /to)?");
        Matcher wrongMatcher2 = wrongPattern2.matcher(input);

        if (correctMatcher.matches()) {
            LocalDate fromDate = LocalDate.parse(correctMatcher.group(3));
            LocalDate toDate = LocalDate.parse(correctMatcher.group(4));
            if (toDate.isBefore(fromDate)) {
                throw new WrongInputException("Your end date is earlier than your start date!");
            }
            return new Event(correctMatcher.group(1),
                    fromDate, toDate);
        } else if (wrongMatcher1.matches()) {
            throw new WrongInputException("Hmmm... This event doesn't have a name!");
        } else if (wrongMatcher2.matches()) {
            throw new WrongInputException("You forgot to specify a start and end date!");
        } else {
            //Shouldn't reach if all error cases handled
            throw new WrongInputException("Something's wrong!");
        }
    }

    /**
     * Returns start date of event.
     */
    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns end date of event.
     */
    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Returns a response message indicating that a new event task has been added.
     * <p>
     * This method is used to generate a confirmation message when a new event task is created.
     * </p>
     *
     * @return a string message indicating the addition of a new event task
     */
    public String creationResponse() {
        return "Ok! I've added a new event:\n" + this
                + "\n";
    }

    /**
     * Returns the string format of the event task suitable for saving to a file.
     * <p>
     * This method includes the task's name, start date, and end date, separated by commas,
     * and ends with a newline character.
     * </p>
     *
     * @return the file format string of the event task
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + "," + startDate + "," + endDate + System.lineSeparator();
    }

    /**
     * Returns a string representation of the event task.
     * <p>
     * This method includes a prefix indicating that the task is an event item, followed by the task's
     * completion status, name, and formatted start and end dates.
     * </p>
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy EEE");
        String dateString = " (from: " + startDate.format(formatter) + " to: "
                + endDate.format(formatter) + ")";
        return "[E]" + super.toString() + dateString;
    }
}
