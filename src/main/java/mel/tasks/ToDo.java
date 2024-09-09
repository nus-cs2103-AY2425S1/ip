package mel.tasks;

import mel.exceptions.TaskException;

/**
 * ToDo class that represents a task, without a
 * date and/or time associated to said task.
 */
public class ToDo extends Task {

    /**
     * Constructs new ToDo task.
     * @param input task input, in the format: todo *task*
     */
    public ToDo(String input) throws TaskException {
        try {
            task = input.split(" ", 2)[1].trim();
            if (task.isEmpty()) {
                throw new TaskException("todo <task>");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskException("todo <task>");
        }
    }

    @Override
    public String toString() {
        assert !task.isEmpty() : "task field should not be empty";
        return "[T]" + super.toString() + task;
    }
}
