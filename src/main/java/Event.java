public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description.strip());
        StringBuilder str = new StringBuilder(start.strip());
        str.insert(str.indexOf(" "), ':');
        this.start = str.toString();
        str = new StringBuilder(end.strip());
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
