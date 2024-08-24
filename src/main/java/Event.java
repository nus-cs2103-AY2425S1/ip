public class Event extends Task {
    private String symbol = "E";
    private String start;
    private String end;

    /**
     * Initialises a Event Task with name, start and end.
     * @param name A string of the Task's name.
     * @param start A string indicating the Task's start date / time.
     * @param end A string indicating the Task's end date / time.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }
    @Override
    public String getSymbol() {
        return "E";
    }

    @Override
    public String getTaskInfo() {
        return super.getTaskInfo() + String.format(" (from: %s to: %s)", this.start, this.end);
    }
}
