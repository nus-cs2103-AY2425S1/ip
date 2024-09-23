package lutodo.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task{
    protected String ddl;
    protected LocalDate deadLine;

    /** Constructs a deadline task with information, including description and ddl date.
     *
     * @param wholeInfo Information of the task, including description and ddl date.
     */
    public DeadlineTask(String wholeInfo) {
        super(splitInfo(wholeInfo)[0]);
        String[] info = splitInfo(wholeInfo);
        ddl = info[1].split("\\s+", 2)[1];
        deadLine = manageDate(ddl);
    }

    /**
     * Splits the task description from the whole line of message. Other information,
     * containing the ddl date, is in the other string.
     *
     * @param wholeInfo The information to be divided.
     * @return The array containing the task description and ddl date.
     */
    public static String[] splitInfo(String wholeInfo) {
        return wholeInfo.split("/", 2);
    }

    /**
     * Returns The message containing basic information of this task.
     * This task: Type of task: Deadline, whether it is done, description and ddl date
     *
     * @return The message containing basic information of this task.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + super.description + " /by: "
                + deadLine.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
