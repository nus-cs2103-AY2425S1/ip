public class Deadlines extends Task {
    private String time;

    public Deadlines(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toFile() {
        return "D | " + (super.isDone() ? "1" : "0") + " | " + super.getDescription() + " | " + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
    
}
