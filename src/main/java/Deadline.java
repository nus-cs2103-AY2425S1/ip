public class Deadline extends Task{
    private String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getCorrectFormat() {
        return String.format("%s | %s", getDescription(), by);
    }

    @Override
    public String toString() {
        return " [D] " + super.toString() + " (by: " + by + ")";
    }
}
