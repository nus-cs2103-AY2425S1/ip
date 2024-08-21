public class Deadline extends Task {
    private final String endDateTime;

    public Deadline(String description, String endDateTime) {
        super(description);
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + endDateTime + ")";
    }

    public static Deadline generateFromString(String string) {
        // [description] /by [deadline]
        String regex = "^(.*?)\\s+/by\\s+(.*)$";

        if (!string.matches(regex)) {
            return null;
        }

        String description = string.replaceAll(regex, "$1").trim();
        String by = string.replaceAll(regex, "$2").trim();

        return new Deadline(description, by);
    }
}
