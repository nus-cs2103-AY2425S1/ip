public class Deadline extends Task{
    private String dueBy;

    public Deadline(String str, String due) {
        super(str);
        this.dueBy = due.trim();
    }

    @Override
    public String toString() {
        String str = this.isDone ? "[D][X] " : "[D][ ] ";
        str += this.description.trim() + " (by: " + this.dueBy + ")";
        return str;
    }
}
