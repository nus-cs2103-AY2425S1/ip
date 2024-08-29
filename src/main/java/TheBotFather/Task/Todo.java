package TheBotFather.Task;

import TheBotFather.util.TheBotFatherException;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Todo extends Task {

    public Todo(String task) throws TheBotFatherException {
        super(task, "T");
    }

    public static Todo makeTodo(StringTokenizer tokens) throws TheBotFatherException {
        try {
            StringBuilder description = new StringBuilder();
            String token = tokens.nextToken();
            description.append(token).append(" ");
            while (tokens.hasMoreTokens()) {
                token = tokens.nextToken();
                description.append(token).append(" ");
            }
            return new Todo(description.toString().trim());
        } catch (NoSuchElementException e) {
            throw new TheBotFatherException("If you have a todo, type : \"todo <description>\"");
        }
    }
}
