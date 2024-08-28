package oyster.utils;

import oyster.tasks.DeadlineTask;
import oyster.tasks.EventTask;
import oyster.tasks.Task;
import oyster.tasks.ToDoTask;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskParser {
    private static String FILE_DELIMITER = "~ ~";

    public static Task parse(String line) {
        String[] lineInfo = line.split(FILE_DELIMITER);

        Task task;

        switch (lineInfo[0]) {
        case ToDoTask.FILE_SYMBOL:
            task = new ToDoTask(lineInfo[2]);
            if (Objects.equals(lineInfo[1], "1")) {
                task.mark();
            }
            break;
        case DeadlineTask.FILE_SYMBOL:
            task = new DeadlineTask(lineInfo[2],
                    LocalDateTime.parse(lineInfo[3]));
            if (Objects.equals(lineInfo[1], "1")) {
                task.mark();
            }
            break;
        case EventTask.FILE_SYMBOL:
            task = new EventTask(lineInfo[2],
                    LocalDateTime.parse(lineInfo[3]),
                    LocalDateTime.parse(lineInfo[4]));
            if (Objects.equals(lineInfo[1], "1")) {
                task.mark();
            }
            break;
        default:
            throw new RuntimeException(); // TODO
        }

        return task;
    }

    public static String compose(Task task) {
        return String.join(FILE_DELIMITER, task.compose());
    }
}
