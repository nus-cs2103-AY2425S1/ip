package jag;

/**
 * This class extends Task and is responsible for setting the description
 * of a todos task and providing the String representation when called upon
 */
public class Todo extends Task {
    protected String by;

    public Todo(String description) {
        super(description);
    }

    public void update(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
