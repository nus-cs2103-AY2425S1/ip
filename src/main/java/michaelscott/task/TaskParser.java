package michaelscott.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import michaelscott.utils.MichaelScottException;

/**
 * Handles the task parser class.
 */
public class TaskParser {

    /**
     * Takes a line from the input file and returns an appropriate task.
     * @Params String line;
     */
    public static Task parseTask(String line) throws MichaelScottException {
        String[] split = line.split(" \\| ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Task task;
        int status = Integer.parseInt(split[1]);
        String name = split[2];
        String taskType = split[0].trim();

        task = switch (taskType) {
        case "T" -> new Todo(name);
        case "D" -> new Deadline(name, LocalDateTime.parse(split[3], formatter));
        case "E" ->
                new Event(name, LocalDateTime.parse(split[3], formatter), LocalDateTime.parse(split[4], formatter));
        case "P" ->
                new Period(name, LocalDate.parse(split[3], dateFormatter),
                        LocalDate.parse(split[4], dateFormatter));
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
