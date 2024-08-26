public class Event extends Task{
    
    protected String start;
    protected String end;
    
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    
    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }
    
    public String storageFormat() {
        return "E " + super.storageFormat() + " | " + start + " | " + end + "\n";
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start  + " to: " + end + ")" + "\n";
    }
}
