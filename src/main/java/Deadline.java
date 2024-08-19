public class Deadline extends Task{
    public Deadline(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[D]%s", super.toString());
    }
}
