public class EventTask extends Task {
    private final String SYMBOL = "E";
    private String from;
    private String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)", this.SYMBOL, super.getStatusIcon(), super.description,
                this.from, this.to);
    }
}
