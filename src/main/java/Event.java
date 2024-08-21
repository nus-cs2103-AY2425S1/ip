import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Event extends Task {
  protected String from;
  protected String to;

  public Event(String description, String from, String to) {
    super(description);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + "(from: " + from + "to:  " + to + ")" ;
  }
  
  public static Event parseEvent(StringTokenizer tokenizedInput) throws NoSuchElementException {
        StringBuilder description = new StringBuilder();
        String token = tokenizedInput.nextToken();
        while (!token.equals("/from")) {
            description.append(token).append(" ");
            token = tokenizedInput.nextToken();
        }
        StringBuilder from = new StringBuilder();
        token = tokenizedInput.nextToken();
        while (!token.equals("/to")) {
            from.append(token).append(" ");
            token = tokenizedInput.nextToken();
        }
        StringBuilder to = new StringBuilder();
        token = tokenizedInput.nextToken();
        to.append(token).append(" ");
        while (tokenizedInput.hasMoreTokens()) {
            token = tokenizedInput.nextToken();
            to.append(token).append(" ");
        }
        return new Event(description.toString(), from.toString(), to.toString());
    }

}
