public class Deadline extends Task {
    private String time;

    public Deadline(String text, String time) {
        super(text);
        this.time = time;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}