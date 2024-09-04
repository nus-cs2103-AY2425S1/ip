public class Deadline extends Task {
    private final String time;

    public Deadline(String text, String time, boolean isDone) {
        super(text,isDone);
        this.time = time;
    }
    @Override
    public String export() {
        return "D | " + (isDone ? "1" : "0") + " | " + text + " | " + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}