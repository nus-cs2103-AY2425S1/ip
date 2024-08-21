public class Event extends Task {
    private String description;
    private String from;
    private String to;

    public Event(String line) throws LukeException {
        super(line);
        int firstSlashIndex = line.indexOf(" /from ");
        if (firstSlashIndex == -1) {
            if (line.contains("/from")) {
                throw new LukeException("There needs to be spacing between /from and other words.");
            }
            else {
                throw new LukeException("Missing /from to indicate the start time of the event.");
            }
        } else {
            int secondSlashIndex = line.indexOf(" /to ");
            if (secondSlashIndex == -1) {
                if (line.contains("/to")) {
                    throw new LukeException("There needs to be spacing between /to and other words.");
                } else {
                    throw new LukeException("Missing /to to indicate the end time of the event.");
                }
            } else {
                description = line.substring(0, firstSlashIndex).trim();
                from = line.substring(firstSlashIndex + 6, secondSlashIndex).trim();
                to = line.substring(secondSlashIndex + 4).trim();
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", super.completedStringRepresentation(), description, from, to);
    }
}
