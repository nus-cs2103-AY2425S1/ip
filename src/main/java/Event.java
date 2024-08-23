public class Event extends Task {

    protected String startDateTime;
    protected String endDateTime;
    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.marker = "/from";
    }

    // Returns the letter representing event.
    @Override
    public String taskLetter() {
        return "E";
    }


}
