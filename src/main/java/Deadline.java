public class Deadline extends Task{
    public String deadline;

    public Deadline(String name, int number, String deadline) {
        super(name, number);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.deadline + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (completed ? 1 : 0) + " | " + name + " | " + deadline;
    }
}

