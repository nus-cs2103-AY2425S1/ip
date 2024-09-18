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
    public static Task create(String message) throws Exception {
        if (Objects.equals(message, null)) {
            throw new EmptyTextException();
        }
        assert message != null : "Empty message exception not thrown";
        Parser p = new Parser(message);
        String m1 = p.getFirstHalf();
        String m2 = p.getSecondHalf();
        if (Objects.equals(m2, null)) {
            throw new WrongMessageException();
        }
        assert m2 != null : "Wrong message exception not thrown";
        Parser q = new Parser(m2, "\\|");
        String description = q.getFirstHalf();
        String isDone = q.getSecondHalf();

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
