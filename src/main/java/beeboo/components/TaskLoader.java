package beeboo.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import beeboo.task.Deadlines;
import beeboo.task.Events;
import beeboo.task.Tasks;
import beeboo.task.ToDos;


/**
 * The {@code TaskCreator} class provides utility methods for creating different types of tasks.
 * It supports creating ToDos, Deadlines, and Events based on input parameters.
 * The created tasks can also be marked as done if specified.
 */
public class TaskLoader {

    /**
     * Creates a {@link Tasks} object based on the provided input parameters.
     *
     * @param task An array of strings representing the task parameters.
     *             The array should have the format:
     *             <ul>
     *                 <li>For ToDos: [ "T", "isDone", "description" ]</li>
     *                 <li>For Deadlines: [ "D", "isDone", "description", "yyyy-MM-ddTHH:mm" ]</li>
     *                 <li>For Events: [ "E", "isDone", "description", "start yyyy-MM-ddTHH:mm",
     *                 "end yyyy-MM-ddTHH:mm" ]</li>
     *             </ul>
     *             <p>The {@code isDone} field is "1" for done, and "0" for not done.</p>
     * @return A new {@link Tasks} object, or {@code null} if the input is invalid.
     */
    public static Tasks createTask(String[] task) {
        Tasks newTask = null;
        if (task.length < 3 || task.length > 6) {
            return null;
        }
        switch (task[0].trim()) {
        case "T":
            newTask = createToDos(task);
            break;
        case "D":
            newTask = createDeadline(task);
            break;
        case "E":
            newTask = createEvent(task);
            break;
        default:
            break;
        }
        return newTask;
    }

    /**
     * Creates a {@link ToDos} task.
     *
     * @param task An array of strings representing the ToDos parameters.
     *             Expected format: [ "T", "isDone", "description" ]
     * @return A new {@link ToDos} task.
     */
    public static ToDos createToDos(String[] task) {
        boolean isDone = task[1].trim().equals("1");
        ToDos newToDos = new ToDos(task[2].trim());
        if (isDone) {
            newToDos.markDone();
        }
        return newToDos;
    }

    /**
     * Creates a {@link Deadlines} task.
     *
     * @param task An array of strings representing the Deadlines parameters.
     *             Expected format: [ "D", "isDone", "description", "yyyy-MM-ddTHH:mm" ]
     * @return A new {@link Deadlines} task.
     */
    public static Deadlines createDeadline(String[] task) {
        boolean isDone = task[1].trim().equals("1");
        String[] dateTime = task[3].split("T");
        LocalDateTime dates = LocalDateTime.of(LocalDate.parse(dateTime[0].trim()),
                LocalTime.parse(dateTime[1].trim()));
        Deadlines newDeadline = new Deadlines(task[2].trim(), dates);
        if (isDone) {
            newDeadline.markDone();
        }
        return newDeadline;
    }

    /**
     * Creates an {@link Events} task.
     *
     * @param task An array of strings representing the Events parameters.
     *             Expected format: [ "E", "isDone", "description", "start yyyy-MM-ddTHH:mm", "end yyyy-MM-ddTHH:mm" ]
     * @return A new {@link Events} task.
     */
    public static Events createEvent(String[] task) {
        boolean isDone = task[1].trim().equals("1");
        String[] startDateTime = task[3].split("T");
        String[] endDateTime = task[4].split("T");
        LocalDateTime startDate = LocalDateTime.of(LocalDate.parse(startDateTime[0].trim()),
                LocalTime.parse(startDateTime[1].trim()));
        LocalDateTime endDate = LocalDateTime.of(LocalDate.parse(endDateTime[0].trim()),
                LocalTime.parse(endDateTime[1].trim()));
        Events newEvent = new Events(task[2].trim(), startDate, endDate);
        if (isDone) {
            newEvent.markDone();
        }
        return newEvent;
    }
}
