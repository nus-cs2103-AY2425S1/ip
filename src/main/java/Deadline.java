public class Deadline extends Task {
    public String deadline;

    public Deadline(String t, String d) {
        super(t);
        this.deadline = d;
        this.type = "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline + ")";
    }


}