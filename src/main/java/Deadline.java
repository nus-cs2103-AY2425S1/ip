import java.util.StringTokenizer;

public class Deadline extends Task{
    protected String by;

    public Deadline(String task, String by) throws TheBotFatherException {
        super(task);
        this.by = by.substring(0, by.length() - 1);

    }

    @Override
    public String toString() {
        return "[D]" + super.toString()+ " (by: " + this.by + ")";
    }
}
