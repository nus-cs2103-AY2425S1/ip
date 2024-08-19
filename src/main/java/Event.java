public class Event extends Task{
    private String from;
    private String to;

    public Event(String taskDescription, String from, String to){
        super(taskDescription, false);
        this.from = from;
        this.to = to;
    }

    private final String TASK_ICON = "[E]";

    @Override
    public String toString() {
        return TASK_ICON + super.toString() +  " (from: " + this.from
                    + " to: " + this.to + ")";
    }
}
