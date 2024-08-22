public class EventTask extends Task {
    String start;
    String end;

    public EventTask(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public static EventTask of(String input) {
        return new EventTask(
                input.substring(6 , input.indexOf("/from")).strip(),
                input.substring(input.indexOf("/from") + 6 , input.indexOf("/to")).strip(),
                input.substring(input.indexOf("/to") + 4).strip()
        );
    }
    @Override
    public String toString() {
        if (this.status) {
            return "[E][X] " + this.description + " (from: " + start  + " to: " + end + ")";
        } else {
            return "[E][ ] " + this.description + " (from: " + start  + " to: " + end + ")";
        }
    }
}
