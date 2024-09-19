package pixel.task;

import pixel.PixelException;

/**
 * Represents a Todo task. A Todo task is a type of task that has a description
 * and can be marked as done or not done.
 */
public class Todo extends Task {
    private String type = "T";

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description the description of the Todo task
     * @throws PixelException if the description is empty
     */
    public Todo(String description) throws PixelException {
        super(modifyDescription(description));
    }

    /**
     * Constructs a Todo task with the given description and done status.
     *
     * @param description the description of the Todo task
     * @param done        the done status of the Todo task
     */
    public Todo(String description, String done) {
        super(description, done);
    }

    /**
     * Modifies the description of the Todo task.
     *
     * @param des the description to be modified
     * @return the modified description
     * @throws PixelException if the description is empty
     */
    private static String modifyDescription(String des) throws PixelException {
        if (des.length() == 0) {
            throw new PixelException("OH NO!!! The description of Todo cannot be empty!");
        }
        return des;
    }

    /**
     * Returns the type of the task.
     *
     * @return the type of the task
     */
    @Override
    public String getType() {
        return this.type;
    }
}
