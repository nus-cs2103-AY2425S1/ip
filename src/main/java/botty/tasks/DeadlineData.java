package botty.tasks;

import botty.commands.ParsedInput;

/**
 * Holds the data that defines a {@code Deadline}
 */
public class DeadlineData extends TaskData {
    // The end date of the deadline
    private String endDate;

    /**
     * Constructor for {@code DeadlineData}
     * @param parsedInput a mapping of flags to values obtained from user input
     */
    public DeadlineData(ParsedInput parsedInput) {
        super(parsedInput);
        this.endDate = parsedInput.getArgumentOrNull("by");
    }

    /**
     * Constructor for {@code DeadlineData}
     * @param description the description of the {@code Deadline}
     * @param endDate the end date of the {@code Deadline}
     */
    public DeadlineData(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    /**
     * Returns the end date as a string
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Returns true if the end date is not empty and not null
     */
    public boolean hasEndDate() {
        return endDate != null && !endDate.isEmpty();
    }
}
