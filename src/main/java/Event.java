public class Event extends Task {
    private String start;
    private String end;

    public Event(String details, String start, String end) {
        super(details);
        this.start = start;
        this.end = end;
    }

    /**
     * Format start and end date to write into file
     * @return Returns start and end time as text
     */
    public String getDatesAsText() {
        return "|" + start + "/" + end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), start, end);
    }


}
