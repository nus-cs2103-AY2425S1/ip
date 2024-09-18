package sunny;

import java.time.LocalDate;

/**
 * Represent tasks with deadlines
 */
public class DeadlineTask extends Task {
    private String m1;
    private String deadline;
    private LocalDate d;
    /**
     * Initialises a Deadline Task object
     * @param description description of the task
     * @param isDone if the event is done
     */
    public DeadlineTask(String description, boolean isDone) throws WrongMessageException {
        super(description, isDone);
        Parser p = new Parser(description, "/by ");
        m1 = p.getFirstHalf();
        deadline = p.getSecondHalf();
        d = LocalDate.parse(deadline.trim());
        if (d.isBefore(LocalDate.now())) {
            throw new WrongMessageException();
        }
    }
    /**
     * Initialises a Deadline task object and set the isDone to false
     * @param description description for the task
     */
    public DeadlineTask(String description) {
        super(description);
        Parser p = new Parser(description, "/by ");
        m1 = p.getFirstHalf();
        deadline = p.getSecondHalf();
        d = LocalDate.parse(deadline.trim());
    }

    @Override
    public void changeTimeline(LocalDate d) {
        this.d = d;
    }
    @Override
    public String getTimeline() {
        return deadline;
    }
    @Override
    public String toString() {
        if (isDone) {
            return String.format("[D][X] %s (by: %s)", m1, d.toString());
        } else {
            return String.format("[D][] %s (by: %s)", m1, d.toString());
        }
    }
}
