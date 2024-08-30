package tars;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Class represents a generic class with a Description and isDone status
 * Serves as a base class for other Task types like (ToDos, Deadline, Event)
 *
 * @author csk
 * @version 1
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for new Task with specified description
     * The task will be initialised with marked as not done
     *
     * @param description (description of Task)
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon depending on the isDone status of the Task
     *
     * @return "X" if task is done, " " otherwise returned
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns status and description of the task
     *
     * @return String Representation of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFileFormat() {
        return "";
    }
}
/**
* Class represents ToDos task, which only has a description
* Extends from Task class
*
* @see Task
*/
class ToDos extends Task {
    /**
     * Constructs for new ToDos Task with description stated
     *
     * @param description (of specified ToDos task)
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the status and description of the task with "[T]"
     *
     * @return String Representation of ToDos task
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toFileFormat() {
        if (isDone) {
            return "T " + "1 " + description;
        } else {
            return "T " + "0 " + description;
        }
    }
}
/**
 * Class represents Deadline Task, which has a specific deadline
 * Extends from Task class and also has an extra field for deadline of task
 *
 * @see Task
 */
class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs new Deadline Task with deadline stated
     *
     * @param description (of specified ToDos task)
     * @param by          (deadline of Task)
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the description of the Deadline task with "[D]"
     *
     * @return String Representation of Deadline task
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toFileFormat() {
        if (isDone) {
            return "D " + "1 " + description + " (by: " + by + " )";
        } else {
            return "D " + "0 " + description + " (by: " + by + " )";
        }
    }
}
/**
 * Class represents Event task, which has a description, and start and end time
 * Extends from Task class and has extra fields to store start and end time of event
 *
 * @see Task
 */
class Event extends Task {
    protected LocalDateTime from, to;

    /**
     * Constructs new Deadline Task with deadline stated
     *
     * @param description (of specified ToDos task)
     * @param from        (start of Event)
     * @param to          (end of Event)
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the description of the Event task with "[E]"
     * String also includes information on Start and End time of Event
     *
     * @return String Representation of Event task
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toFileFormat() {
        if (isDone) {
            return "E " + "1 " + description + "(from: " + from + " to: " + to + " )";
        } else {
            return "E " + "0 " + description + "(from: " + from + " to: " + to + " )";
        }
    }
}

