package thebotfather.task;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import thebotfather.util.TheBotFatherException;

/**
 * Represents a simple task that needs to be done without any specific time frame.
 * A Todo is a type of Task that only has a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param task The description of the todo task.
     * @throws TheBotFatherException If the task description is invalid.
     */
    public Todo(String task) throws TheBotFatherException {
        super(task, "T");
    }

    /**
     * Creates a new Todo object from a StringTokenizer input.
     * The input should contain the task description.
     *
     * @param tokens The StringTokenizer containing the input string.
     * @return A new Todo object.
     * @throws TheBotFatherException If the input format is incorrect or if the description is empty.
     */
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
            throw new TheBotFatherException("Why to do a todo if there is no todo to do :/ \n"
                    + "If you have a todo, type : \"todo <description>\"");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Todo)) {
            return false;
        }

        return obj.toString().equals(this.toString());
    }
}
