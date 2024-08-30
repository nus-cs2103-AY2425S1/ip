package michaelscott.task;

import michaelscott.utils.MichaelScottException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskParser {
    public static Task parseTask(String line) throws MichaelScottException {
        String[] split = line.split(" \\| ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Task task;
        int status = Integer.parseInt(split[1]);
        String name = split[2];
        String taskType = split[0].trim();

        task = switch (taskType) {
            case "T" -> new Todo(name);
            case "D" -> new Deadline(name, LocalDateTime.parse(split[3], formatter));
            case "E" ->
                    new Event(name, LocalDateTime.parse(split[3], formatter), LocalDateTime.parse(split[4], formatter));
            default -> throw new MichaelScottException("Wrong stuff");
        };

        if (status == 1) {
            task.completeTask();
        } else {
            task.undoTask();
        }

        return task;
    }
}
