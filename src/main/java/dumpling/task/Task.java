package dumpling.task;

import dumpling.DumplingException;

/**
 * Abstract Task class, which Event, Deadline and ToDo inherit from
 */
public abstract class Task {
    protected String description;
    protected String notes;
    protected boolean isDone;

    /**
     * General constructor of tasks objects
     * @param description task descriptions
     * @param notes supporting notes for task
     */
    public Task(String description, String notes) {
        this.description = description;
        this.notes = notes;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     *
     * @return 'X' if marked, ' ' otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Checks if the description of this task has the given substring.
     * This method does not check the notes of the task.
     *
     * @param substring Target substring to search for
     * @return True if substring is part of description, false otherwise
     */
    public boolean hasSubstring(String substring) {
        for (int i = 0; i <= description.length() - substring.length(); i++) {
            if (this.description.startsWith(substring, i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Mark item as done
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Unmark item as done
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    public void updateNotes(String updatedNotes) throws DumplingException {
        // do not allow new line characters in notes as it will interfere with saving format
        if (updatedNotes.contains("\n")) {
            throw new DumplingException("New line characters are not allowed in notes!");
        }
        this.notes = updatedNotes;
    }

    /**
     * Get the string representation of the task in the format for saving
     *
     * @return String representation of task in saving format
     */
    public abstract String getTaskForSaving();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
