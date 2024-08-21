import java.util.StringTokenizer;

public class Todo extends Task {

  public Todo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

  public static Todo parseTodo(StringTokenizer tokenizedInput) {
        StringBuilder description = new StringBuilder();
        String token = tokenizedInput.nextToken();
        description.append(token).append(" ");
        while (tokenizedInput.hasMoreTokens()) {
            token = tokenizedInput.nextToken();
            description.append(token).append(" ");
        }
        return new Todo(description.toString());
    }
}
