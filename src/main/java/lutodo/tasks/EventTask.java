package lutodo.tasks;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task.
 */
public class EventTask extends Task{
    protected String startTime;
    protected LocalTime start;
    protected String endTime;
    protected LocalTime end;

    /** Constructs an event task with information, including description, start time and end time.
     *
     * @param wholeInfo Information of the task, including description, start time and end time.
     */
    public EventTask(String wholeInfo) {
        super(splitInfo(wholeInfo)[0]);
        String[] info = splitInfo(wholeInfo);
        startTime = info[1].split("\\s+", 2)[1].trim();
        endTime = info[2].split("\\s+", 2)[1].trim();
        start = manageTime(startTime);
        end = manageTime(endTime);
    }

    /**
     * Splits the task description from the whole line of message to the first string of
     * an Array of Strings.
     * The start time, is in the second string of the array,
     * The end time, is in the third string of the array.
     *
     * @param wholeInfo The information to be divided.
     * @return The array containing the task description, start time and end time.
     */
    public static String[] splitInfo(String wholeInfo) {
        return wholeInfo.split("/", 3);
    }

    /**
     * Returns The message containing basic information of this task.
     * This task: Type of task: Event, whether it is done, description, start time and end time.
     *
     * @return The message containing basic information of this task.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + super.description
                + " /from: " + start.format(DateTimeFormatter.ofPattern("HH:mm"))
                + " /to: " + end.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
