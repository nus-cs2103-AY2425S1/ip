package cstwooneohthree.glados.tasks;

import cstwooneohthree.glados.enums.TaskType;

/**
 * Todo class that basic task with description.
 *
 * @author jayjay19630
 */
public class Todo extends Task {

    /**
     * Constructs new Todo object by calling parent.
     *
     * @param description Description of Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     * Returns Todo task type icon.
     *
     * @return [T] icon.
     */
    @Override
    public String getTaskTypeIcon() {
        return "[T]";
    }

    /**
     * {@inheritDoc}
     * Returns Todo task type.
     *
     * @return Todo enum.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    /**
     * {@inheritDoc}
     * Returns Todo description.
     *
     * @return Todo string.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * {@inheritDoc}
     * Returns Todo string for saving into data folder.
     *
     * @return Todo save format string.
     */
    @Override
    public String getSaveFormat() {
        return this.description;
    }
}
