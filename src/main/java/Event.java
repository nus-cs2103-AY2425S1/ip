public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return String.format("[E]%s(from: %s to: %s)",super.toString(),this.from,this.to);
    }

    @Override
    public String toSaveString() {
        if (isDone) {
            return String.format("E | %d | %s",1,this.getDescription());
        } else {
            return String.format("E | %d | %s",0,this.getDescription());
        }
    }
}