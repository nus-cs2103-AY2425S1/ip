public class Deadline extends Task
{
    public String by;
    public String statement;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String write()
    {
        statement = String.format("D | %d | %s | %s\n", this.isDone, this.description, this.by);
        return statement;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
