public class Event extends Task {

    protected String startDay;
    protected String startTime;
    protected String endTime;

    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Event(String description, String startDay, String startTime, String endDateTime) {
        super(description);
        this.startDay = startDay;
        this.startTime = startTime;
        this.endTime = endDateTime;
        this.marker = "/from";
    }

    /**
     * Constructor to initialise a task preivously recorded.
     *
     * @param description Input based on user.
     */
    public Event(String description, String startDay, String startTime, String endDateTime, boolean isDone) {
        super(description, isDone);
        this.startDay = startDay;
        this.startTime = startTime;
        this.endTime = endDateTime;
        this.marker = "/from";
    }

    // Returns the letter representing event.
    @Override
    public String taskLetter() {
        return "E";
    }

    /**
     * Returns a string representation of the file format in which we store the Event.
     */
    @Override
    public String fileFormat () {
        String part1 = super.fileFormat();
        return part1 + " | " + startDay + " | " + startTime + " | " + endTime;
    }
}
