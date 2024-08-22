import java.util.Objects;

public class TaskCreator {
    public static Tasks create(String message) {
        String m1 = message.split(" ", 2)[0];
        if (Objects.equals(m1, "todo")) {
            return new TodoTasks(message.split(" ", 2)[1]);
        } else if (Objects.equals(m1, "deadline")) {
            return new DeadlineTasks(message.split(" ", 2)[1]);
        } else if (Objects.equals(m1, "event")) {
            return new EventTasks(message.split(" ", 2)[1]);
        } else {
            return new Tasks(message);
        }
    }
}
