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
            task = input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskException("todo <task>");
        }
    }

    @Override
    public String toString() {
        String mark = super.toString();
        return "[T]" + mark + task;
    }
}
