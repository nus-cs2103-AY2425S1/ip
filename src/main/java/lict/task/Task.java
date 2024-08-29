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

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void isMarked(boolean b) {
        this.isDone = b;
    }

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