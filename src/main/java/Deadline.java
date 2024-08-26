public class Deadline extends Task{
    private final String time;
    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }
    @Override
    public String toString() {
        if (super.isComplete()) {
            return ("[D] " + super.toString() + " (by: " + this.time + ")");
        }
        return ("[D] " + super.toString() + " (by: " + this.time + ")");
    }
}
