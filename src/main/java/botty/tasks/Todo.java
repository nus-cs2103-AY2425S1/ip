package botty.tasks;

import botty.exceptions.BottyException;
import botty.exceptions.CorruptedTaskStringException;
import botty.exceptions.EmptyArgumentException;

/**
 * A Todo
 */
public class Todo extends Task<TodoData> {
    /**
     * Constructs a {@code Todo} with the given completion status and description
     * @param isCompleted whether the task is completed
     * @param data the data involved in generating the {@code Todo}
     */
    public Todo(boolean isCompleted, TodoData data) throws EmptyArgumentException {
        super(isCompleted, data);
    }
    /**
     * Constructs a {@code Todo} with the given description, set as not completed
     * @param data the data involved in generating the {@code Todo}
     */
    public Todo(TodoData data) throws EmptyArgumentException {
        this(false, data);
    }

    /**
     * Returns a string representation of the {@code Todo}
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Constructs a {@code Todo} from a data string for loading from file
     * @param taskString the data string from file
     * @return the constructed task
     * @throws BottyException if corrupted task string or invalid arguments
     */
    public static Todo getTodoFromDataString(String taskString) throws BottyException {
        if (!taskString.matches("T \\| [10] \\| (.*?)")) {
            throw new CorruptedTaskStringException();
        }

        String[] arguments = taskString.split(" \\| ");

        boolean completed = arguments[1].equals("1");
        String description = arguments[2];

        return new Todo(completed, new TodoData(description));
    }
    /**
     * Returns a string representation of the {@code Todo} that is used for local storage
     */
    @Override
    public String getDataString() {
        return "T | " + getCompletedAndDescription();
    }

    /**
     * Returns the task type of the task
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    /**
     * Updates the task with the given data
     */
    @Override
    public void update(TodoData data) throws BottyException {
        super.update(data);
    }
}
