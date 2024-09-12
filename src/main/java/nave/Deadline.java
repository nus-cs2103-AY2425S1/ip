package nave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code Deadline} class represents a task with a deadline, including a name and a due date.
 * <p>
 * It extends the {@code Task} class to add functionality for handling tasks that have a specific due date.
 * This includes parsing input to create a {@code Deadline} instance, formatting the task for display, and
 * converting it to a file-compatible format.
 * </p>
 */
public class Deadline extends Task {
    private final LocalDate endDate;

    /**
     * Constructs a {@code Deadline} task with the specified name and due date.
     *
     * @param name the name of the deadline task
     * @param endDate the due date for the deadline task
     */
    public Deadline(String name, LocalDate endDate) {
        super(name);
        this.endDate = endDate;
    }

    /**
     * Creates a new {@code Deadline} instance based on the provided input string.
     * <p>
     * The method uses regular expressions to validate the input and ensure it contains both a valid task name
     * and a properly formatted due date. If the input does not match the expected pattern, appropriate
     * {@code WrongInputException}s are thrown based on the nature of the input error.
     * </p>
     *
     * @param input the input string containing the task name and due date
     * @return a {@code Deadline} object if the input is valid
     * @throws WrongInputException if the input contains errors such as incorrect date format or missing details
     */
    public static Deadline handleInput(String input) throws WrongInputException {
        Pattern correctPattern = Pattern.compile("((\\w+\\s*)+) /by (\\d{4}-\\d{2}-\\d{2})");
        Matcher correctMatcher = correctPattern.matcher(input);

        Pattern wrongPattern1 = Pattern.compile("(\\s*|\\s*/by((.+\\s*)*))");
        Matcher wrongMatcher1 = wrongPattern1.matcher(input);

        Pattern wrongPattern2 = Pattern.compile("((.+\\s*)*) (/by)?");
        Matcher wrongMatcher2 = wrongPattern2.matcher(input);

        Pattern wrongPattern3 = Pattern.compile("((\\w+\\s*)+) /by (\\d+-\\d+-\\d+|.+)");
        Matcher wrongMatcher3 = wrongPattern3.matcher(input);

        if (correctMatcher.matches()) {
            LocalDate date = LocalDate.parse(correctMatcher.group(3));
            return new Deadline(correctMatcher.group(1), date);
        } else if (wrongMatcher3.matches()) {
            throw new WrongInputException("Your date format is wrong!");
        } else if (wrongMatcher1.matches()) {
            throw new WrongInputException("Hmmm... This deadline doesn't have a name!");
        } else if (wrongMatcher2.matches()) {
            throw new WrongInputException("You forgot to specify a due date!");
        } else {
            //Shouldn't reach if all error cases handled
            throw new WrongInputException("Something's wrong!");
        }
    }

    /**
     * Returns a response message indicating that a new deadline task has been added.
     * <p>
     * This method is used to generate a confirmation message when a new deadline
     * task is created.
     * </p>
     *
     * @return a string message indicating the addition of a new deadline task
     */
    public String creationResponse() {
        return "Ok! I've added a new task with a deadline:\n" + this
                + "\n";
    }

    /**
     * Returns the string format of the deadline task suitable for saving to a csv file.
     * <p>
     * This method includes the task's name and due date, separated by a comma, and ends with a newline character.
     * </p>
     *
     * @return the file format string of the deadline task
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + "," + endDate.toString() + System.lineSeparator();
    }

    /**
     * Returns a string representation of the deadline task.
     * <p>
     * This method includes a prefix indicating that the task is a deadline item, followed by the task's
     * completion status, name, and due date formatted as "dd MMM yyyy EEE".
     * </p>
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy EEE");
        String endDateString = " (by: " + endDate.format(formatter) + ")";
        return "[D]" + super.toString() + endDateString;
    }
}
