public class Event extends Task{
    private String from;
    private String to;
    public Event(String input) {
        super(input.substring(0, input.indexOf("/from") - 1));
        int indexFrom = input.indexOf("/from");
        int indexTo = input.indexOf("/to");
        from = input.substring(indexFrom + 6, indexTo);
        to = input.substring(indexTo + 4);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
