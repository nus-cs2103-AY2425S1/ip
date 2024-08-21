public class Deadline extends Task {
    private String time;
    public Deadline(String body, String time) {
        super(body);
        this.time = time;
    }

    @Override
    public String toString() {
        String str = String.format("[D]%s (due by: %s)", super.toString(), this.time);
        return str;
    }
}
