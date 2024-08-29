import java.util.StringTokenizer;

public class Deadline extends Task{
    protected String by;

    protected Deadline(String description, String by) throws TheBotFatherException {
        super(description, "D");
        this.by = by;

    }

    protected static Deadline makeDeadline(StringTokenizer tokens) throws TheBotFatherException {
        StringBuilder description = new StringBuilder();
        StringBuilder by = new StringBuilder();

        String token = tokens.nextToken();
        while (!token.equals("/by")) {
            description.append(token).append(" ");
            token = tokens.nextToken();
        }

        token = tokens.nextToken();
        by.append(token).append(" ");
        while (tokens.hasMoreTokens()) {

            token = tokens.nextToken();
            by.append(token).append(" ");
        }

        return new Deadline(description.toString().substring(0, description.length() - 1), by.toString().substring(0, by.length() - 1));
    }

    @Override
    public String toString() {
        return super.toString()+ " (by: " + this.by + ")";
    }

    @Override
    protected String toFile() {
        return super.toFile() + " | " + this.by;
    }
}
