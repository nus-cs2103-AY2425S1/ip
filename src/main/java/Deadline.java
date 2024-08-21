public class Deadline extends Task {
    private String endTime;

    public Deadline(String description) {
        // TODO: Deal with errors associated with input
        super(description.split("/by ")[0]);
        this.endTime = description.split("/by ")[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.endTime + ")";
    }
}
