public class EventTask extends Task {
    String start;
    String end;

    public EventTask(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTaskType() {
        return "[E]";
    }
    @Override
    public String getDescription() {
        return this.getTaskType() + super.getDescription().replace("\n", "")
                + String.format("(from:%s to:%s)", this.start, this.end);
    }

}
