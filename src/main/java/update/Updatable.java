package update;

/**
 * Interface for updating specific fields of a task object.
 * Classes that implement this interface should define how
 * to update a task's field with a new value.
 */
public interface Updatable {
    void updateTask(String field, String newValue);
}
