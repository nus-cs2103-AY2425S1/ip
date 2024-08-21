import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Deadline extends Task {
  protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public static Deadline parseDeadline(StringTokenizer tokenizedInput) throws NoSuchElementException {
        StringBuilder description = new StringBuilder();
        String token = tokenizedInput.nextToken();
        while (!token.equals("/by")) {
            description.append(token).append(" ");
            token = tokenizedInput.nextToken();
        }
        StringBuilder by = new StringBuilder();
        token = tokenizedInput.nextToken();
        by.append(token).append(" ");
        while (tokenizedInput.hasMoreTokens()) {
            token = tokenizedInput.nextToken();
            by.append(token).append(" ");
        }
        return new Deadline(description.toString(), by.toString());
    }
}
