package mahesh.task;

import java.util.StringTokenizer;

import mahesh.util.MaheshException;

/**
 * Represents a recurring task which is a specific type of Task.
 * A Recurring task has a description and a day on which it recurs.
 */
public class Recurring extends Task {
    private String day;

    /**
     * Constructs a new Recurring task with the specified description and day.
     *
     * @param description The description of the Recurring task.
     * @param dayString The day on which the task recurs.
     */
    public Recurring(String description, String dayString) {
        super(description);
        this.day = dayString;
    }

    /**
     * Constructs a new Recurring task with the specified description, day, and completion status.
     *
     * @param description The description of the Recurring task.
     * @param dayString The day on which the task recurs.
     * @param isDone The completion status of the Recurring task.
     */
    public Recurring(String description, String dayString, boolean isDone) {
        super(description, isDone);
        this.day = dayString;
    }

    /**
     * Returns a string representation of the Recurring task, including its type indicator and recurring day.
     *
     * @return A string representation of the Recurring task.
     */
    @Override
    public String toString() {
        return "[R]" + super.toString() + " every " + day;
    }

    /**
     * Converts the Recurring task to a string format suitable for saving to a file.
     * The format includes the type indicator "R", the completion status, the description
     * of the task, and the recurring day.
     *
     * @return A string representation of the Recurring task for file storage.
     */
    @Override
    public String toFileEntry() {
        return "R/" + super.toFileEntry() + "/" + day;
    }

    /**
     * Parses a StringTokenizer to create a new Recurring task.
     *
     * @param tokenizedInput The StringTokenizer containing the description and day of the Recurring task.
     * @return A new Recurring task with the parsed description and day.
     * @throws MaheshException If the input does not contain the expected tokens.
     */
    public static Recurring parseRecurring(StringTokenizer tokenizedInput) throws MaheshException {
        try {
            StringBuilder description = new StringBuilder();
            String token = tokenizedInput.nextToken();
            while (!token.equals("/every")) {
                description.append(token).append(" ");
                token = tokenizedInput.nextToken();
            }
            String dayString = tokenizedInput.nextToken();
            return new Recurring(description.toString().trim(), dayString);
        } catch (Exception err) {
            throw new MaheshException("Please follow the given format: recurring <recurring_desc> /every <day_string>");
        }
    }
}
