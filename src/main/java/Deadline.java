public class Deadline extends Task {
    private final String endDateTime;

    public Deadline(String description, String endDateTime) throws EmptyArgumentException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyArgumentException("description");
        }
        if (endDateTime.isEmpty()) {
            throw new EmptyArgumentException("end date time");
        }
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + endDateTime + ")";
    }
}
