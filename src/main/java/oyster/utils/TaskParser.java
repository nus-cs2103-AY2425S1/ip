package oyster.utils;

import java.time.LocalDateTime;
import java.util.Objects;

import oyster.exceptions.ParseException;
import oyster.tasks.DeadlineTask;
import oyster.tasks.EventTask;
import oyster.tasks.Task;
import oyster.tasks.ToDoTask;

/**
 * Parses Tasks.
 */
public class TaskParser {
    private static final String FILE_DELIMITER = "~ ~";

    /**
     * Parses a String into Task.
     *
     * @param line The line to be parsed into a Task.
     * @return Task object.
     */
    public static Task parse(String line) {
        assert !line.isBlank();

        String[] lineInfo = line.split(FILE_DELIMITER);

        Task task;

        switch (lineInfo[0]) {
        case ToDoTask.FILE_SYMBOL:
            task = new ToDoTask(lineInfo[2]);
            if (Objects.equals(lineInfo[1], "1")) {
                task.mark();
            } else if (!Objects.equals(lineInfo[1], "0")) {
                throw new ParseException();
            }
            break;
        case DeadlineTask.FILE_SYMBOL:
            task = new DeadlineTask(lineInfo[2],
                    LocalDateTime.parse(lineInfo[3]));
            if (Objects.equals(lineInfo[1], "1")) {
                task.mark();
            } else if (!Objects.equals(lineInfo[1], "0")) {
                throw new ParseException();
            }
            break;
        case EventTask.FILE_SYMBOL:
            task = new EventTask(lineInfo[2],
                    LocalDateTime.parse(lineInfo[3]),
                    LocalDateTime.parse(lineInfo[4]));
            if (Objects.equals(lineInfo[1], "1")) {
                task.mark();
            } else if (!Objects.equals(lineInfo[1], "0")) {
                throw new ParseException();
            }
            break;
        default:
            throw new ParseException();
        }

        return task;
    }

    /**
     * Converts the Task into String.
     *
     * @param task The Task to be converted into String.
     * @return Writable save String of a Task.
     */
    public static String compose(Task task) {
        assert task != null;

        return String.join(FILE_DELIMITER, task.compose());
    }
}
