package silverwolf.task;

import silverwolf.exception.SilverWolfException;

/**
 * represents an abstract task with a description and completion status.
 * a Task can be marked as done or undone and has a specific TaskType.
 */
public abstract class Task {
    protected TaskType type;
    private String description;
    private boolean isDone;
    /**
     * constructs a Task with the specified description and type.
     *
     * @param description The description of the task.
     * @param type The type of the task, defined by the TaskType enum.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
    }

    /**
     * Converts a string representation of a task into an appropriate Task object.
     *
     * The input string is expected to follow a specific format:
     * "TaskType | isDone | description | additionalInfo" where:
     * - TaskType indicates the type of task (TODO, DEADLINE, or EVENT).
     * - isDone is a flag indicating whether the task is completed ("1" for done, "0" for not done).
     * - description is the text description of the task.
     * - additionalInfo provides extra details specific to the task type (e.g., deadline date or event times).
     *
     * The method parses the input string, creates the corresponding Task object, sets its completion status,
     * and returns the Task object.
     *
     * @param str The string representation of the task to be converted.
     * @return A Task object corresponding to the input string.
     * @throws SilverWolfException If the task type is unknown or if there is an error in parsing the input string.
     */
    public static Task fromString(String str) throws SilverWolfException {
        String[] parts = str.split(" \\| ");
        TaskType taskType = TaskType.fromString(parts[0]);
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
        case TODO:
            Todo todo = new Todo(description);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case DEADLINE:
            String by = parts[3];
            Deadline deadline = new Deadline(description, by);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        case EVENT:
            String[] eventTimes = parts[3].split("-");
            String from = eventTimes[0];
            String to = eventTimes[1];
            Event event = new Event(description, from, to);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            throw new SilverWolfException("Unknown task type: " + taskType);
        }
    }

    /**
     * Marks this task as done by setting its completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks this task, setting its completion status to false.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns a status icon indicating whether the task is done.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getFileIcon() {
        return (isDone ? "1" : "0");
    }
    /**
     * Retrieves the description of this task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the type of the task.
     * This method must be implemented by subclasses.
     *
     * @return The TaskType of the task.
     */
    protected abstract TaskType type();

    /**
     * Returns a string representation of the task for file storage.
     * The format includes the task type, status icon, description, and deadline.
     *
     * @return A string in the format depending on the type of task.
     * @see Event#toFileString() For the string format used when saving events.
     * @see Deadline#toFileString() For the string format used when saving events.
     * @see Todo#toFileString() For the string format used when saving events.
     */
    public abstract String toFileString();



}
