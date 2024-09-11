package chatbot.impl.task;

import chatbot.interfaces.AbstractTask;

public class Task implements AbstractTask {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * <p>Marks the task as completed or not completed</p>
     * @param status of the task
     */
    public void setDone(boolean status) {
        this.isDone = status;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X
    }

    /**
     * <p>
     *     Returns a string representation of the task
     *     Used for printing or viewing
     * </p>
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * <p>
     *     Returns a serialized representation of the task
     *     Used for data transfer or storage
     * </p>
     * @return a string representation of the task
     */
    public String serialize() {
        return this.getStatusIcon() + "|" + this.description;
    }

}
