import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    private LocalDateTime deadline;
    private String deadlineStr;

    /**
     * Constructs a new 'Deadline' task that can be added to Tayoo's list. A Deadline is defined as a task
     * that has a deadline that it needs to be completed before
     *
     * @param title title of the Deadline task
     * @param deadline Deadline by which the task should be completed
     */
    public Deadline(String title, LocalDateTime deadline, boolean completed) {
        super(title, completed);
        this.deadline = deadline;
    }

    /**
     * Constructs a new 'Deadline' task that can be added to Tayoo's list. A Deadline is defined as a task
     * that has a deadline that it needs to be completed before. Assumes new Deadline is not completed.
     *
     * @param title title of the Deadline task
     * @param deadline Deadline by which the task should be completed
     */
    public Deadline(String title, LocalDateTime deadline) {
        super(title);
        this.deadline = deadline;
    }

    /**
     * Constructs a new 'Deadline' task that can be added to Tayoo's list. A Deadline is defined as a task
     * that has a deadline that it needs to be completed before
     *
     * @param title title of the Deadline task
     * @param deadline Deadline by which the task should be completed
     */
    public Deadline(String title, String deadline, boolean completed) {
        super(title, completed);
        this.deadlineStr = deadline;
        this.deadline = null;
    }

    /**
     * Constructs a new 'Deadline' task that can be added to Tayoo's list. A Deadline is defined as a task
     * that has a deadline that it needs to be completed before. Assumes new Deadline is not completed.
     *
     * @param title title of the Deadline task
     * @param deadline Deadline by which the task should be completed
     */
    public Deadline(String title, String deadline) {
        super(title);
        this.deadlineStr = deadline;
        this.deadline = null;
    }

    @Override
    public String toString() {
        if (this.deadline != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

            return "[D]" + super.toString() + " (by: " + deadline.format(dateFormatter) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + this.deadlineStr + ")";
        }
    }

    /**
     * Returns the Deadline in a form that can be stored as data in a .txt file
     * Tasks will be stored in the format "Deadline" | [true OR false] | [Deadline title] | [deadline]. The value true
     * will be stored if the Deadline is completed, and false otherwise.
     *
     * @return string representation of Deadline in command form
     */
    public String toTxt() {
        if (this.isCompleted()) {
            return "Deadline | true | " + this.getTitle() + " | " + this.deadline;
        } else {
            return "Deadline | false | " + this.getTitle() + " | " + this.deadline;
        }
    }
}
