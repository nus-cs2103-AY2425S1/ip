public class Deadline extends Task{
    private final String time;
    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }
    @Override
    public String toString() {
        if (super.isComplete()) {
            return ("[D] " + super.toString() + "(by: " + this.time + ")");
        }
        return ("[D] " + super.toString() + "(by: " + this.time + ")");
    }
}
