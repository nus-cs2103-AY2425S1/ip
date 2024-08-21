public class Deadline extends Task {
    private String endTime;

    public Deadline(String text) {
        // TODO: Deal with errors associated with input
        super(text.split("/by ")[0]);
        this.endTime = text.split("/by ")[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.endTime + ")";
    }
}
