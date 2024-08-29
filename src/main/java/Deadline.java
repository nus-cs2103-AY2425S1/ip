public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getCsvFormat() {
        return "D,"+ super.getCsvFormat() + "," + by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
