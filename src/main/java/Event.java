public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String str) {
        super(str.split("/")[0].split(" ", 2)[1]);
        String[] temp = str.split("/");
        from = temp[1].split(" ", 2)[1];
        to = temp[2].split(" ", 2)[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "
                + from + " to: " + to + ")";
    }
}
