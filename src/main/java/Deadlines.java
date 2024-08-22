public class Deadlines extends Task {
    protected String desc;

    public Deadlines(String desc) throws EmptyDeadlineException, EmptyDeadlineDateException {
        super(desc);
        //throw exception if task desc not given
        if (desc.equals("")) {
            throw new EmptyDeadlineException("     OOPS!!! The description of a deadline cannot be empty leh.");
        }
        //throw exception if date not given
        String[] parts = desc.split(" /by ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EmptyDeadlineDateException("     OOPS!! Deadline date not given leh!");
        }
    }

    @Override
    public String print() {
        //splits string to get the due date
        String[] parts = super.print().split(" /by ");
        return "[D]" + parts[0] + " (by: " + parts[1] + ")";
    }
}

