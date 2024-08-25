public class EventTask extends Task {

    private String start;
    private String end;
    public EventTask(String description, String start, String end) throws IllegalInputPotongException {
        super(description);
        if (start.isEmpty() || end.isEmpty()) {
            throw new IllegalInputPotongException();
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}
