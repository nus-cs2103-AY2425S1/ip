public class Deadline extends Task {

    public String endTime;

    public Deadline(String name, String endTime) {
        super(name);
        this.endTime = endTime;
    }

    public Deadline(String name, String endTime, boolean done) {
        super(name, done);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime + ")";
    }

    @Override
    public String toData() {
        return "D" + super.toData() + "%" + this.endTime;
    }
}
