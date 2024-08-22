public class Deadline extends Task {
    private String date;

    public Deadline(String name) {
        super(name.split("/by")[1]);
        this.date = name.split("/by")[1];
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.date);
    }
}
