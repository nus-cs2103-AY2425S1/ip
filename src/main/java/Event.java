public class Event extends Task {
    String start;
    String end;
    public Event(String s, String start, String end) {
        super(s);
        this.start = start;
        this.end = end;
    }
    // event meeting /from today /to friday
    public static Event createEvent(String input) throws EmptyDescriptionException {
        String eventDescription = input.substring(5).trim();
        if (eventDescription.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        String eventName = eventDescription.split(" /")[0];
        String eventStart = eventDescription.split(" /")[1].substring(5);
        String eventEnd = eventDescription.split (" /")[2].substring(3);
        return new Event(eventName, eventStart, eventEnd);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)" , super.toString(), this.start, this.end);
    }
}
