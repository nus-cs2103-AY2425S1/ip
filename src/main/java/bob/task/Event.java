package bob.task;

/**
 * Day task that has a start time and end time.
 */
public class Event extends Task {

    private static final String TASK_LETTER = "E";
    protected String startDay;
    protected String startTime;
    protected String endTime;


    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Event(String description, String startDay, String startTime, String endTime) {
        super(description);
        this.startDay = startDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor to initialise a task previously recorded.
     *
     * @param description Input based on user.
     */
    public Event(String description, String startDay, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startDay = startDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor to initialise a task previously recorded.
     *
     * @param description Input based on user.
     */
    public Event(String description, String startDay, String startTime, String endTime, boolean isDone, String tag) {
        super(description, isDone, tag);
        this.startDay = startDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Returns the letter representing event.
    @Override
    public String getTaskLetter() {
        return TASK_LETTER;
    }

    /**
     * Returns a string representation of the file format in which we store the Event.
     */
    @Override
    public String getFileFormat() {
        String eventFileFormat = super.getFileFormat();
        eventFileFormat += " | " + startDay + " | " + startTime + " | " + endTime;
        if (!getTag().equals("")) {
            eventFileFormat += " | " + getTag();
        }
        return eventFileFormat;
    }

    /**
     * Returns a string representation for an event task in the printed list.
     */
    @Override
    public String getTaskListItem() {
        return super.getTaskListItem() + " (from: " + startDay + " " + startTime + " to: " + endTime + ")";
    }

    /**
     * Tags the task.
     *
     * @param tag Tag.
     */
    @Override
    public void tagTask(String tag) {
        this.setTag(tag);
    }

    /**
     * Tags the task.
     */
    @Override
    public void untagTask() {
        this.setTag("");
    }
}
