public class Deadlines extends Task{
    protected String desc;

    public Deadlines(String desc) {
        super(desc);
    }

    @Override
    public String print() {
        //splits string to get the due date
        String[] parts = super.print().split(" /by ");
        return "[D]" + parts[0] + " (by: " + parts[1] + ")";
    }
}
