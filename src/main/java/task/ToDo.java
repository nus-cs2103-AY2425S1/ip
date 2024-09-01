package task;

import exceptions.AlreadyCompletedException;

/**
 * A todo task.
 *
 * @author IsaacPangTH
 */
public class ToDo extends Task {

    /**
     * Constructor for a <code>ToDo</code>.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }


    /**
     * Factory method which creates a<code>ToDo</code>from a string containing data of the todo.
     *
     * @param data Data of the ToDo in form "completion status|description".
     * @return <code>ToDo</code>from data.
     */
    public static ToDo of(String data) {
        String[] args = data.split("\\|");
        ToDo todo = new ToDo(args[1]);
        if (Boolean.parseBoolean(args[0])) {
            try {
                todo.complete();
            } catch (AlreadyCompletedException e) {
                throw new RuntimeException(e);
            }
        }
        return todo;
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }
}
