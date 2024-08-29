package lict.task;

import lict.LictException;

/**
 * The abstract {@code lict.task.Task} class represents a task with a description and a status indicating whether it is done.
 * It provides methods to manage the task's completion status and to convert it to a string representation or data representation for saving and loading.
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A enum of valid task types.
     */
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
    }

    /**
     * Constructs a {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the task as marked or unmarked.
     *
     * @param b {@code true} if the task should be marked as done, {@code false} otherwise.
     */
    public void isMarked(boolean b) {
        this.isDone = b;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representation of the task in the format "[status] description".
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public boolean containsKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }
    /**
     * Converts the task to a string format suitable for saving to a data file.
     * This method must be implemented by subclasses to provide their specific data format.
     *
     * @return A string representing the task in a format suitable for data storage.
     */
    public abstract String toData();

    /**
     * Converts a string of task data back into a {@code Task} object.
     *
     * @param data The string containing the task data.
     * @return The {@code Task} object created from the data string, or {@code null} if the data is invalid.
     */
    public static Task convertData(String data) {
        String[] dataParts = data.split("\\|", 3);
        if (dataParts.length < 3) {
            return null;
        }
        try {
            TaskType taskType = TaskType.valueOf(dataParts[0].trim().toUpperCase());
            boolean isDone = dataParts[1].trim().equals("1");
            String description = dataParts[2].trim();
            Task task;

            switch (taskType) {
            case TODO:
                task = Todo.loadTask(description);
                break;

            case DEADLINE:
                task = Deadline.loadTask(description);
                break;

            case EVENT:
                task = Event.loadTask(description);
                break;

            default:
                // Discard data and move on to next iteration
                return null;
            }

            task.isMarked(isDone);
            return task;

        } catch (IllegalArgumentException | LictException e) {
            return null;
        }
    }
}