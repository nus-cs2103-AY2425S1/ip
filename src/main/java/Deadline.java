public class Deadline extends Task {
    public final String deadline;

    public Deadline(String t, String d) {
        super(t);
        this.deadline = d;
        this.type = "[D]";
    }

    public static void load(String[] arr) {
        new Deadline(arr[1], arr[2]);
    }

    @Override
    public String saveFileFormat() {
        return "E | " + this.getTask() + " | " + this.deadline;
    }
    @Override
    public String toString() {
        return super.toString() + " (By: " + this.deadline + ")";
    }


}