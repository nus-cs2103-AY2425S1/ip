public class Deadline extends Task{

    protected String by;

    public Deadline(String desc, String by){
        super(desc);
        this.by = by;
    }

    public String stringifyTask() {
        return String.format("D | %d | %s | %s", super.getStatus() ? 1 : 0, super.getDesc(), this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
