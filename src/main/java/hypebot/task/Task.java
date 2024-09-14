package hypebot.task;

import java.time.LocalDate;
import java.util.regex.Pattern;

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
     * @param name The name of the Task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the task as user entered.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Takes in a Pattern containing keywords and returns whether
     * the Task's name contains any of the keywords in the Pattern.
     *
     * @param searchQuery Pattern of keywords to search for.
     * @return Whether the Task's name contains any of the keywords.
     */
    public boolean nameContains(Pattern searchQuery) {
        return searchQuery.matcher(name.toLowerCase()).find();
    }

    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Marks Task as complete by changing isComplete to true.
     */
    public void mark() {
        this.isComplete = true;
    }

    /**
     * Marks Task as incomplete by changing isComplete to false.
     */
    public void unmark() {
        this.isComplete = false;
    }

    /**
     * Takes in a LocalDate object representing a search date
     * and returns whether the task is happening on the given date.
     *
     * @param date LocalDate object representing a date.
     * @return False - Task base class does not have a date associated.
     */
    public boolean isHappeningOn(LocalDate date) {
        return false;
    }

    /**
     * Returns the String description of the Task to append to /data/tasklist.txt.
     * Should not be accessed at runtime.
     *
     * @return String description of Task to append to /data/tasklist.txt.
     */
    public String toFileString() {
        return "N , 0 , T\n";
    }

    /**
     * Returns the String representation of the task as shown to the user on the HypeBot UI.
     * Should be in this form: "[{X only if complete}] {name}".
     *
     * @return String representation of task as shown on HypeBot UI.
     */
    @Override
    public String toString() {
        return "[" + (isComplete ? "X" : " ") + "] " + this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task task) {
            return this.name.equals(task.name);
        }
        return false;
    }
}
