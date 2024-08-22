public class Event extends Task {
    private String start;
    private String end;

    public Event(String... params) throws IllegalArgumentException {
        super(params[0].strip());
        if (params.length < 2) {
            throw new IllegalArgumentException("Event: Did not provide start and end date/time");
        } else if (params.length < 3) {
            throw new IllegalArgumentException("Event: Did not provide end date/time");
        }

        StringBuilder str = new StringBuilder(params[1].strip());
        str.insert(str.indexOf(" "), ':');
        this.start = str.toString();
        str = new StringBuilder(params[2].strip());
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
