public class Deadline extends ToDo {
    public Deadline(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
