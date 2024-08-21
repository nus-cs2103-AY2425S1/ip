public class Deadline extends Task {
    public Deadline(String task) {
        super(task);
    }

    public String toString() {
        return "[D]" + super.toString();
    }


}
