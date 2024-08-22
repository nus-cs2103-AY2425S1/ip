public class Event extends Task {
    private String start;
    private String end;

    public Event(String... parems) throws IllegalArgumentException {
        super(parems[0].strip());
        if (parems.length < 2) {
            throw new IllegalArgumentException("Event: Did not provide start and end date/time");
        } else if (parems.length < 3) {
            throw new IllegalArgumentException("Event: Did not provide end date/time");
        }

        StringBuilder str = new StringBuilder(parems[1].strip());
        str.insert(str.indexOf(" "), ':');
        this.start = str.toString();
        str = new StringBuilder(parems[2].strip());
        str.insert(str.lastIndexOf(" "), ":");
        this.end = str.toString();
    }

    @Override
    public String toString() {
        String mark = super.isDone() ? "X" : " ";
        return String.format("[E][%s] %s (%s %s)",
                mark, super.getDescription(), start, end);
    }
}
