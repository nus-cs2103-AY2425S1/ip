public class Event extends Task {
    String description;
    String from;
    String to;

    public Event(String line) {
        super(line);
        int firstSlashIndex = line.indexOf("/from");
        int secondSlashIndex = line.indexOf("/to");
        description = line.substring(0, firstSlashIndex).trim();
        from = line.substring(firstSlashIndex + 5, secondSlashIndex).trim();
        to = line.substring(secondSlashIndex + 3).trim();
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", super.completedStringRepresentation(), description, from, to);
    }
}
