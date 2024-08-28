public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return String.format("[D]%s(by: %s)",super.toString(),this.by);
    }

    @Override
    public String toSaveString() {
        if (isDone) {
            return String.format("D | %d | %s",1,this.getDescription());
        } else {
            return String.format("D | %d | %s",0,this.getDescription());
        }
    }
}
