public class Deadline extends Task{
    private String deadline;
    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline.trim();
    }

    @Override
    public String toString() {
        String str = this.completed ? "[D][X] " : "[D][ ] ";
        str += String.format("%s (by:%s)", this.description, this.deadline);
        return str;
    }
}
