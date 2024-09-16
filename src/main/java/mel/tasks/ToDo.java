package mel.tasks;

import mel.exceptions.TaskException;

import java.time.format.DateTimeFormatter;

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
    /**
     * Provides task string in save file format.
     * @return task string.
     */
    @Override
    public String toSaveString() {
        assert !task.isEmpty() : "task field should not be empty";
        String mark = super.toSaveString();
        return "T|" + mark + "|" + task;
    }

    /**
     * Provides task string for user response.
     * @return task string.
     */
    @Override
    public String toString() {
        assert !task.isEmpty() : "task field should not be empty";
        String mark = super.toString();
        return "[T]" + mark + task;
    }
}
