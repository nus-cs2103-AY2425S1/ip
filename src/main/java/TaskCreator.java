import java.util.Objects;

public class TaskCreator {
    public static Tasks create(String message) throws Exception{
        String m1 = message.split(" ", 2)[0];
        if (Objects.equals(m1, "todo")) {
            if (message.split(" ", 2).length == 1) {
                throw new EmptyTextException();
            }
            return new TodoTasks(message.split(" ", 2)[1]);
        } else if (Objects.equals(m1, "deadline")) {
            if (message.split(" ", 2).length == 1) {
                throw new EmptyTextException();
            }
            return new DeadlineTasks(message.split(" ", 2)[1]);
        } else if (Objects.equals(m1, "event")) {
            if (message.split(" ", 2).length == 1) {
                throw new EmptyTextException();
            }
            return new EventTasks(message.split(" ", 2)[1]);
        } else {
            throw new WrongMessageException();
        }
    }
}
