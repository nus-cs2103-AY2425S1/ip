public class Event extends Task{
    private String from;
    private String to;

    public Event(String taskDescription, String from, String to){
        super(taskDescription, false);
        this.from = from;
        this.to = to;
    }

    public String serializeDetails() {
        return "E !- " + this.getIsDone() + "!- "
                + this.getTaskDescription() + "!- "
                + this.from + "!- "
                + this.to + "\n";
    }
    private final String TASK_ICON = "[E]";

    @Override
    public String toString() {
        return TASK_ICON + super.toString() +  " (from: " + this.from
                    + " to: " + this.to + ")";
    }
}
