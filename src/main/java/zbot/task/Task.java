package zbot.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Note note;

    /**
     * Constructor for Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.note = new Note();
    }

    /**
     * Constructor for Task with note.
     *
     * @param description Description of the task.
     * @param note        Note of the task.
     */
    public Task(String description, Note note) {
        this.description = description;
        this.isDone = false;
        this.note = note;
    }

    /**
     * Constructor for Task with note content.
     *
     * @param description Description of the task.
     * @param note        Note content of the task.
     */
    public Task(String description, String note) {
        this.description = description;
        this.isDone = false;
        this.note = new Note(note);
    }

    /**
     * Returns the status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Adds a note to the task.
     */
    public void addNote(String content) {
        this.note = new Note(content);
    }

    /**
     * Returns the note of the task.
     *
     * @return Note of the task.
     */
    public Note getNote() {
        return note;
    }

    @Override
    public String toString() {
        return note.isEmpty()
                ? String.format("[%s] %s", getStatusIcon(), description)
                : String.format("[%s] %s (Note: %s)", getStatusIcon(), description, note);
    }

}
