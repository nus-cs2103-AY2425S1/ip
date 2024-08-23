public class Event extends IndividualTask{

    private String from;
    private String to;
    public Event(String taskDescription, String from, String to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
    }
    @Override
    public String saveToFileFormat() {
        return "E | " + this.getSaveIcon() + " | " + this.getTaskDescription() + " | " + this.from + " | " + this.to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
