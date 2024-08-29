public class Event extends Task {

    private String to;
    private String from;
    public Event(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.to = to;
        this.from = from;
    }

    public static Task from(String text) {
        String[] parameters = text.split("\\s\\|\\s");
        return new Event(parameters[2], parameters[3], parameters[4], parameters[1].equals("1"));

    }

    @Override
    public String toText() {
        return String.format("E | %s | %s | %s | %s", super.isDone ? 1 : 0, super.description, this.from, this.to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

}
