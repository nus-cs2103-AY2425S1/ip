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
}
