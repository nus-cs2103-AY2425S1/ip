public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Private constructor for a Task
     *
     * @param description A string description of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[E]");
        str.append(super.toString());
        str.append(String.format("(From:%sTo:%s)", from, to));
        return str.toString();
    }
}
