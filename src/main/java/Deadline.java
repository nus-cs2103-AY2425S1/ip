public class Deadline extends Task {
    private String byTime;

    public Deadline(String name, String description, String byTime) {
        super(name, description);
        this.byTime = byTime;
    }

    public String getByTime() {
        return byTime;
    }

    public void setByTime(String byTime) {
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() +
                "\n\tBy: " + byTime;
    }
}
