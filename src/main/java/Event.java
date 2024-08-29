import java.util.Objects;
import java.util.StringTokenizer;

public class Event extends Task{
    protected String from;
    protected String to;

    protected Event(String description, String from, String to) throws TheBotFatherException {
        super(description, "E");
        this.from = from;
        this.to = to;
    }

    protected static Event makeEvent(StringTokenizer tokens) throws TheBotFatherException{
        StringBuilder description = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        String token = tokens.nextToken();

        while (!token.equals("/from")) {
            description.append(token).append(" ");
            token = tokens.nextToken();
        }

        token = tokens.nextToken();
        while (!token.equals("/to")) {
            from.append(token).append(" ");
            token = tokens.nextToken();
        }

        token = tokens.nextToken();
        to.append(token).append(" ");
        while (tokens.hasMoreTokens()) {
            token = tokens.nextToken();
            to.append(token).append(" ");
        }

        if (Objects.equals(from.toString(), "")) {
            throw new TheBotFatherException("Kid, look at what you have  written... is there a from there?? *sigh*");
        }

        return new Event(description.toString().substring(0, description.length() - 1), from.toString().substring(0, from.length() - 1), to.toString().substring(0, to.length() - 1));
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    protected String toFile() {
        return super.toFile() + " | " + this.from + " | " + this.to;
    }
}
