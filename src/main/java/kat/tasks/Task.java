package kat.tasks;

/**
 * Represents a task in the chatbot system.
 */
public interface Task {

    /**
     * Sets the completion status of the task.
     *
     * @param isDone true if the task is done, false otherwise.
     */
    void setDone(boolean isDone);

    /**
     * Serializes the task to a string representation.
     *
     * @return A string representation of the task.
     */
    String serialize();

}
