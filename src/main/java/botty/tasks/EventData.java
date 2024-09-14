package botty.tasks;

import botty.commands.ParsedInput;

/**
 * Holds the data that defines an {@code Event}
 */
public class EventData extends TaskData {
    // The start date of the event
    private String startDate;
    // The end date of the event
    private String endDate;

    /**
     * Constructor for an {@code EventData}
     * @param parsedInput the data that defines the {@code Event} obtained from user input
     */
    public EventData(ParsedInput parsedInput) {
        super(parsedInput);
        this.startDate = parsedInput.getArgumentOrNull("from");
        this.endDate = parsedInput.getArgumentOrNull("to");
    }

    /**
     * Constructor for an {@code EventData}
     * @param description the description of the {@code Event}
     * @param startDate the start date of the {@code Event}
     * @param endDate the end date of the {@code Event}
     */
    public EventData(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the start date of the {@code Event}
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the {@code Event}
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Returns true if the start date is not null and not empty
     */
    public boolean hasStartDate() {
        return startDate != null && !startDate.isEmpty();
    }

    /**
     * Returns true if the end date is not null and not empty
     */
    public boolean hasEndDate() {
        return endDate != null && !endDate.isEmpty();
    }
}
