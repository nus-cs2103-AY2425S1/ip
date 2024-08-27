public class Deadline extends Task {
    private String time;
    public Deadline(String body, String time) {
        super(body);
        this.time = time;
    }

    public Deadline(String body, boolean isDone, String time) {
        super(body, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (due by: %s)", super.toString(), this.time);
    }

    @Override
    public String saveString() {
        return "deadline," + super.saveString() + "," + this.time;
    }
}
