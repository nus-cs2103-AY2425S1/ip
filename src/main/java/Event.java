public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public static Event getEventFromInput(String input) {
        String[] parts = input.substring(6).split(" /from ");
        String[] dates = parts[1].split(" /to ");

        return new Event(parts[0], dates[0], dates[1]);
    }

    @Override
    public String toString() { 
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")"; 
    }
}
