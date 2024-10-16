package infinity.task;

import infinity.infinityexception.InfinityException;

/**
 * This class is the todo task that the bot will recognise and manage.
 */
public class ToDos extends Task {
    /**
     * Constructor for the ToDos class.
     *
     * @param description The description of the todo.
     */
    public ToDos(String description) {
        this.setDescription(description);
        this.setTypeOfTask(Task.TaskTypes.T);
    }

    /**
     * 2nd Constructor for the ToDos class.
     *
     * @param isDone The status of the todo.
     * @param description The description of the todo.
     * @throws InfinityException If the format of the todo is wrong.
     */
    public ToDos(boolean isDone, String description) throws InfinityException {
        this.isDone = isDone;
        this.setDescription(description);
        this.setTypeOfTask(Task.TaskTypes.T);
    }
}
