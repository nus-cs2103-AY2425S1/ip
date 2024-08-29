import java.util.Objects;
import java.util.StringTokenizer;

public class Parser {
    protected static Task makeTask(StringTokenizer tokens) throws TheBotFatherException{
        if (!tokens.hasMoreTokens()) throw new TheBotFatherException("Error");
        String taskType = tokens.nextToken();
        boolean isDone = Objects.equals(tokens.nextToken(), "1");
        Task task;
        switch (taskType) {
        case "T":
            task = Todo.makeTodo(tokens);
            break;
        case "D":
            task = Deadline.makeDeadline(tokens);
            break;
        case "E":
            task = Event.makeEvent(tokens);
            break;
        default:
            throw new TheBotFatherException("Instruction unclear");
        }
        ;

        if (isDone) task.markAsDone();
        return task;
    }
}
