package elysia.tasks;

/**
 * A stub implementation of the {@link TaskList} class for testing purposes.
 * This class overrides methods from {@link TaskList} to track method calls and simulate behavior
 * for use in unit tests.
 */
public class TaskListStub extends TaskList{
    public boolean isToStringCalled = false;
    public boolean isPrintTaskCalled = false;
    public boolean isDeleteTaskCalled = false;
    public boolean isSizeCalled = false;
    public boolean isAddTaskCalled = false;
    public int taskNumber;
    public Task task;

    /**
     * Overrides the {@link TaskList#addTask(Task)} method to track if it is called.
     * Sets the {@code isAddTaskCalled} flag to {@code true} and stores the provided task.
     *
     * @param task The task to be added to the list.
     */
    @Override
    public void addTask(Task task) {
        isAddTaskCalled = true;
        this.task = task;
    }

    /**
     * Overrides the {@link TaskList#markTask(int)} method to track if it is called.
     * Sets the {@code taskNumber} to the provided number and simulates throwing an exception for invalid indices.
     *
     * @param taskNumber The index of the task to be marked.
     * @throws IndexOutOfBoundsException If the task number is -1.
     */
    @Override
    public void markTask (int taskNumber) throws IndexOutOfBoundsException, NullPointerException {
        if (taskNumber == -1) {
            throw new IndexOutOfBoundsException("task not in tasklist");
        }
        this.taskNumber = taskNumber;
    }

    /**
     * Overrides the {@link TaskList#unmarkTask(int)} method to track if it is called.
     * Sets the {@code taskNumber} to the provided number and simulates throwing an exception for invalid indices.
     *
     * @param taskNumber The index of the task to be unmarked.
     * @throws IndexOutOfBoundsException If the task number is -1.
     */
    @Override
    public void unmarkTask (int taskNumber) throws IndexOutOfBoundsException, NullPointerException {
        if (taskNumber == -1) {
            throw new IndexOutOfBoundsException("task not in tasklist");
        }
        this.taskNumber = taskNumber;
    }

    /**
     * Overrides the {@link TaskList#deleteTask(int)} method to track if it is called.
     * Sets the {@code isDeleteTaskCalled} flag to {@code true} and simulates returning a new {@link Task}.
     * Throws an {@link IndexOutOfBoundsException} if the index is -1.
     *
     * @param index The index of the task to be deleted.
     * @return A new {@link Task} instance.
     * @throws IndexOutOfBoundsException If the index is -1.
     */
    @Override
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        isDeleteTaskCalled = true;
        if (index == -1) {
            throw new IndexOutOfBoundsException();
        }
        return new Task("");
    }

    /**
     * Overrides the {@link TaskList#size()} method to track if it is called.
     * Sets the {@code isSizeCalled} flag to {@code true} and returns an empty string.
     *
     * @return An empty string.
     */
    @Override
    public String size() {
        isSizeCalled = true;
        return "";
    }

    /**
     * Overrides the {@link TaskList#printTask(int)} method to track if it is called.
     * Sets the {@code isPrintTaskCalled} flag to {@code true} and returns an empty string.
     *
     * @param taskNumber The index of the task to be printed.
     * @return An empty string.
     */
    @Override
    public String printTask(int taskNumber) {
        isPrintTaskCalled = true;
        return "";
    }

    /**
     * Overrides the {@link TaskList#toString()} method to track if it is called.
     * Sets the {@code isToStringCalled} flag to {@code true} and returns an empty string.
     *
     * @return An empty string.
     */
    @Override
    public String toString() {
        isToStringCalled = true;
        return "";
    }
}
