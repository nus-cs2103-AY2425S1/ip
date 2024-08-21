public class Event extends Task {
    private final String startDateTime;
    private final String endDateTime;

    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }

    public static Event generateFromString(String string) {
        // [description] /from [start] /to [end]
        String regex ="^(.*?)\\s+/from\\s+(.*?)\\s+/to(.*)$";

        if (!string.matches(regex)) {
            return null;
        }

        String description = string.replaceAll(regex, "$1").trim();
        String from = string.replaceAll(regex, "$2").trim();
        String to = string.replaceAll(regex, "$3").trim();

        return new Event(description, from, to);
    }
}
