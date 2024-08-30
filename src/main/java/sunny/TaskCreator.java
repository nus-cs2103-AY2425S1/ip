package sunny;

import java.util.Objects;

/**
 * Create subclasses of tasks based on user message
 */
public class TaskCreator {
    /**
     * Creates tasks by parsing user message
     * @param message include command, description and timing
     * @return One of the tasks class: todo deadline event
     * @throws Exception if message does not fit the standard
     */
    public static Task create(String message) throws Exception{
        Parser p = new Parser(message);
        String m1 = p.getCommand();
        if (Objects.equals(p.getMessage(), null)) {
            throw new WrongMessageException();
        }
        Parser q = new Parser(p.getMessage(), "\\|");
        String description = q.getCommand();
        String isDone = q.getMessage();

        if (Objects.equals(m1, "todo")) {
            return new TodoTask(description, Objects.equals(isDone, "true"));
        } else if (Objects.equals(m1, "deadline")) {
            return new DeadlineTask(description, Objects.equals(isDone, "true"));
        } else if (Objects.equals(m1, "event")) {
            return new EventTask(description, Objects.equals(isDone, "true"));
        } else {
            throw new WrongMessageException();
        }
    }
}
