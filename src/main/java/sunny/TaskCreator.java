package sunny;

import java.util.Objects;

/**
 * Create subclasses of tasks based on user message
 */
public class TaskCreator {
    /**
     * Creates tasks by parsing user message
     * @param message
     * @return One of the tasks class: todo deadline event
     * @throws Exception
     */
    public static Task create(String message) throws Exception{
        Parser p = new Parser(message);
        String m1 = p.getCommand();

        if (Objects.equals(m1, "todo")) {
            if (message.split(" ", 2).length == 1) {
                throw new EmptyTextException();
            }
            return new TodoTask(message.split(" ", 2)[1]);
        } else if (Objects.equals(m1, "deadline")) {
            if (message.split(" ", 2).length == 1) {
                throw new EmptyTextException();
            }
            return new DeadlineTask(message.split(" ", 2)[1]);
        } else if (Objects.equals(m1, "event")) {
            if (message.split(" ", 2).length == 1) {
                throw new EmptyTextException();
            }
            return new EventTask(message.split(" ", 2)[1]);
        } else {
            throw new WrongMessageException();
        }
    }
}
