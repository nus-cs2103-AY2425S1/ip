public class Event extends Task {
    protected String starttime;
    protected String endtime;

    public Event(String description, String starttime, String endtime) {
        super(description);
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " + starttime + ", to: " + endtime + ")";
    }
    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }
}
