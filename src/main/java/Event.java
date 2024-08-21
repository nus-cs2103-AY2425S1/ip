public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public Task createTask(String input) {
        String[] details = input.substring(6).split(" /from | /to ");
        if (details.length == 3) {
            String description = details[0].trim();
            String from = details[1].trim();
            String to = details[2].trim();
            return new Event(description, from, to);
        } else {
            throw new IllegalArgumentException("Invalid format. Use: event <description> /from <start> /to <end>");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
