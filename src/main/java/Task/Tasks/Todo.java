package Task.Tasks;

import Task.Task;

public class Todo extends Task {
    public Todo(String title) {
        super(title);
    }

    /**
     * Creates a new to-do with title and completion status
     * @param title the title
     * @param done the completion status
     */
    public Todo(String title, boolean done) {
        super(title, done);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Todo: ");
        sb.append(super.toString());
        return sb.toString();
    }
}
