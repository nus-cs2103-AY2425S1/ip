package Task.Tasks;

import Task.Task;

class Todo extends Task {
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
}
