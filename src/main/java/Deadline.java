public class Deadline extends Task {
    private final String task;
    private final String by;

    public Deadline(String str) throws TaskException {
        try {
            String[] temp = str.split("/");
            task = temp[0].split(" ", 2)[1];
            by = temp[1].split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskException("deadline <task> /by <time>");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + task + "(by: " + by + ")";
    }
}
