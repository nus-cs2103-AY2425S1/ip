public class Event extends Task {
    String startTime;
    String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts the details of the file into the format represented in the storage file.
     *
     * @return A string in the appropriate format, E | Completion Status (0 or 1) | Description | Start Time | End Time
     */
    @Override
    public String convertToFileString() {
        return "E | " + super.convertToFileString() + String.format(" | %s | %s\n", startTime, endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", startTime, endTime);
    }
}
