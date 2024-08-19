public interface Task {
    /**
     * Mark the task as completed or not completed.
     * @param isCompleted whether the task is completed
     */
    void mark(boolean isCompleted);

    /**
     * Get the task data as a string.
     * @return the task data as a string
     */
    String toString();
}
