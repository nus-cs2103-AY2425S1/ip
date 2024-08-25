import java.text.MessageFormat;

public class Event extends Task{
    private String description;
    private boolean isDone;
    private static final String TASKTYPE = "E";
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.isDone = false;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private String getStartTime(){
        return this.startTime;
    }

    private String getEndTime(){
        return this.endTime;
    }

    public String toString(){
        return MessageFormat.format("[{0}]{1} (from: {2} to: {3})",
                TASKTYPE,
                super.toString(),
                this.getStartTime(),
                this.getEndTime());
    }
}
