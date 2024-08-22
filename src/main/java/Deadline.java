public class Deadline extends Task {
    private String endDate;

    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        return "[D] " + super.getName() + "(" + this.endDate + ")";
    }
}