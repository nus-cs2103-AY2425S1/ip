package task;

import task.KorolevTask;

/**
 * Represents a task of Todo type
 */
public class KorolevTodo extends KorolevTask {
    private String tag;

    /**
     * Constructs an object of KorolevTodo.
     *
     * @param name description of the todo task
     */
    public KorolevTodo(String name) {
        super(name);

        assert !name.isEmpty();
        this.tag = "T";
    }

    /**
     * Overrides toString method in KorolevTask.
     *
     * @return string representation of KorolevTodo
     */
    @Override
    public String toString() {
        String base = super.toString();
        String head = "[" + this.tag + "]";

        return head + base;
    }
}
