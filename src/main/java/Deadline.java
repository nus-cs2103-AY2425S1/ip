public class Deadline extends Task {
    private String type, dateTime;

    public Deadline(String desc, String type, String dateTime, boolean isDone) {
        super(desc, isDone);
        this.type = type;
        this.dateTime = dateTime;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + "by: " + this.dateTime + ")";
    }
}

