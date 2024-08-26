/**
 * Represents an Event task with a specific time window.
 */
public class Event extends Task {
    private String window;

    /**
     * Constructs an Event task with a given description.
     *
     * @param description The description of the Event task.
     * @throws EmptyInputException if the description is empty.
     */
    public Event(String description) throws EmptyInputException {
        super(description);

        String[] parts = description.split("/");

        this.name = parts[0].trim();

        // Extract the first and second parts after the backslashes, if they exist
        String fromPart = parts.length > 1 ? parts[1].trim() : "";
        String toPart = parts.length > 2 ? parts[2].trim() : "";

        fromPart = fromPart.replaceFirst("^from\\s*", "").trim();
        toPart = toPart.replaceFirst("^to\\s*", "").trim();

        // Add "from:" and "to:" labels
        fromPart = "from: " + fromPart;
        toPart = "to: " + toPart;

        // Reformat the output
        window = "(" + fromPart + " " + toPart + ")";
    }

    /**
     * Returns a string representation of the Event task, including its time window.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.name + " " + window;
    }
}
