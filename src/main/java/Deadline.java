public class Deadline extends Task{
    private String due;

    public Deadline(String str, String due) {
        super(str);
        this.due = due.trim();
    }

    @Override
    public String toString() {
        String str = this.isDone ? "[D][X] " : "[D][ ] ";
        str += this.description.trim() + " (by: " + this.due + ")";
        return str;
    }
}
