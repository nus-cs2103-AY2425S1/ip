package beeboo.task;

import beeboo.exception.NoDescriptionException;

/**
 * The ToDos class represents a to-do task with a description.
 * It extends the Tasks class and provides functionality specific to to-do tasks.
 */
public class ToDos extends Tasks {

    /**
     * Constructs a ToDos instance with the specified description.
     * The task is initially marked as not done.
     *
     * @param description the description of the to-do task
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns the type icon for to-do tasks.
     *
     * @return the type icon as a String
     */
    @Override
    public String typeIcon() {
        return "[T]";
    }

    /**
     * Returns a String representation of the ToDos instance, including the type icon and description.
     *
     * @return a String representation of the to-do task
     */
    @Override
    public String toString() {
        return typeIcon() + super.toString();
    }

    /**
     * Creates a ToDos instance from the given text input. The input should include the description of the task.
     * The method validates the description and returns a new ToDos object.
     *
     * @param text the input text containing the description of the to-do task
     * @return a ToDos instance
     * @throws NoDescriptionException if the description is missing or empty
     */
    public static ToDos createToDo(String text) throws NoDescriptionException {
        if (text.isEmpty()) {
            throw new NoDescriptionException("No description");
        }
        return new ToDos(text);
    }

    /**
     * Returns a String representation of the ToDos instance in a format suitable for saving to storage.
     * The format includes the task type, completion status, and description.
     *
     * @return a String representing the saved format of the to-do task
     */
    @Override
    public String saveFormat() {
        return "T | " + (super.isDone ? "1 | " : "0 | ") + description;
    }

    @Override
    public void updateTime(String time) {

    }
}
