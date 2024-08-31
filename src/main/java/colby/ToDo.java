package colby;

import java.util.Objects;

/**
 * Class that represents todo tasks
 * description refers to what the task is about
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string depiction of the task with the type shown as "[T]", followed by whether the
     * task is marked as done or not and the description of the task
     * @return string of the todo task with its details
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo todo = (ToDo) o;
        return Objects.equals(description, todo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
