public class Event extends Task{

    protected String start;

    protected String end;
    public Event(String description, String start, String end){
        super(description);
        this.start = start;
        this.end = end;

    }

    @Override
    public String toSaveFormat() {
        String storeCompleted = "";

        if (this.isDone) {
            storeCompleted = "1";
        } else {
            storeCompleted = "0";
        }
        // store format: E | 0 | book event | 2pm | 5pm
        return "E | " + storeCompleted + " | " + this.description + " | " + this.start + " | " + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
