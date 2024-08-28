class Event extends Task {
    private final String startTime;
    private final String endTime;

    public Event(String taskDescription, String startTime, String endTime, boolean isDone) {
        super(taskDescription, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String taskDescription, String startTime, String endTime) {
        this(taskDescription, startTime, endTime, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " " + "to: " + this.endTime + ")";
    }
}
