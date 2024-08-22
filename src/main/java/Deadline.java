public class Deadline extends Task {
    private String end;
    public Deadline(String description, String end) throws BobException {
        super(description);
        this.end = end;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + end + ")";
    }
}
