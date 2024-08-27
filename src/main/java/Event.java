public class Event extends Task {
    private String type = "E";

    private static String modifyDescription(String des) throws TaskException {
        if (des.length() == 0) {
            throw new TaskException("OH NO!!! The description of Event cannot be empty!");
        } else if (!des.contains("/from") || !des.contains("/to")) {
            throw new TaskException("Event should be of this format: {description} /from {date} /to {date}");
        }
        return des.replaceFirst("/from", "(from:").replaceFirst("/to", "to:") + ")";
    }

    public Event(String description) throws TaskException {
        super(modifyDescription(description));
    }

    public Event(String description, String done) {
        super(description, done);
    }

    @Override
    public String getType() {
        return this.type;
    }
}
