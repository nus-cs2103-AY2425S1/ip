public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public Task createTask(String input) throws InputException{
        if (input.equalsIgnoreCase("event")) {
            throw new InputException("To add an Event task, use the following format: event <task description> /from <start time> /to <end time>");
        }
        String[] details = input.substring(6).split(" /from | /to ");
        if (details.length == 3) {
            String description = details[0].trim();
            String from = details[1].trim();
            String to = details[2].trim();
            if (description.isEmpty()) {
                throw new InputException("You need to describe your Event!");
            }
            return new Event(description, from, to);
        } else {
            throw new InputException("Invalid format. Use: event <description> /from <start> /to <end>");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
