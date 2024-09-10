package hypebot.task;

import java.time.LocalDate;

/**
 * Represents a Task which all the different types of tasks inherit from.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class Task {
    private boolean isComplete = false;
    private final String name;

    /**
     * Creates a Task with the specified name.
     *
     * @param name The name of the hypebot.task.
     */
    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Marks task as complete by changing isComplete to true.
     */
    public void mark() {
        this.isComplete = true;
    }

    /**
     * Marks task as incomplete by changing isComplete to false.
     */
    public void unmark() {
        this.isComplete = false;
    }

    /**
     * Takes in a LocalDate object representing a search date
     * and returns whether the event is happening on the given date.
     *
     * @param date LocalDate object representing a date.
     * @return False - Task base class does not have a date associated.
     */
    public boolean isHappeningOn(LocalDate date) {
        return false;
    }

    /**
     * Returns the String description of the task to append to /data/tasklist.txt.
     * Should not be accessed at runtime.
     *
     * @return String description of task to append to /data/tasklist.txt.
     */
    public String toFileString() {
        return "N , 0 , T\n";
    }

    /**
     * Returns the String representation of the task as shown to the user on the hypebot.HypeBot UI.
     * Should be in this form: "[{X only if complete}] {name}".
     *
     * @return String representation of hypebot.task as shown on hypebot.HypeBot UI.
     */
    @Override
    public String toString() {
        return "[" + (isComplete ? "X" : " ") + "] " + this.name;
    }
}
