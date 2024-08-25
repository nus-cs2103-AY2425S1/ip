public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructor for Event class.
     * @param description a String describing the Event
     * @param start a String describing the start date/time
     * @param end a String describing the end date/time
     */
    public Event(String description, String start, String end) {
        super(description.strip());
        StringBuilder str = new StringBuilder(start.strip());
        str.insert(str.indexOf(" "), ':');
        this.start = str.toString();
        str = new StringBuilder(end.strip());
        str.insert(str.lastIndexOf(" "), ":");
        this.end = str.toString();
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    /**
     * Returns a formatted String that represents the Event object and its fields
     * @return a String representation of the Event object
     */
    @Override
    public String toString() {
        return String.format("[E]%s (%s %s)",
                super.toString(), start, end);
    }
}
