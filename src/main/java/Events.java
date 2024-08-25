public class Events extends Task {

    String startTime;
    String endTime;
    public Events(String description, String start, String end) {
        super(description);
        this.startTime = start;
        this.endTime = end;
    }

    /**
     * Returns the Event as a string with its status, description, start time and end time
     *
     * @return a String of the Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + startTime + " to: " + endTime + ")";
    }

    /**
     * Returns Event as a fancier string with its status, description, start time and end time
     * Meant for recording in text files
     *
     * @return Fancier string of the Deadline
     */
    @Override
    public String toFancyString() {
        return "Event | " + super.getStatus() + " | " +
                super.getDescription() + " | /from "  + this.startTime + " /to " + this.endTime;
    }
}
