package fridayproject;

public class Deadline extends Tasks {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + date + ")";
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }
}
