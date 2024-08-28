public class Deadlines extends Task {

    /**
     * Calls constructor of super class
     *
     * @throws EmptyDeadlineException If desc is empty.
     * @throws EmptyDeadlineDateException if split desc string has < 2 parts(no date)
     * or second part(date) is empty
     */
    public Deadlines(String desc) throws EmptyDeadlineException, EmptyDeadlineDateException {
        super(desc);

        //throw exception if task desc not given
        if (desc.isEmpty()) {
            throw new EmptyDeadlineException
                    ("     OOPS!!! The description of a deadline cannot be empty leh.");
        }

        //throw exception if date not given
        String[] parts = desc.split(" /by ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EmptyDeadlineDateException
                    ("     OOPS!! Deadline date not given leh!");
        }
    }

    @Override
    public String print() {
        //splits string to get the due date
        String[] parts = super.print().split(" /by ");
        return "[D]" + parts[0] + " (by: " + parts[1] + ")";
    }
}

