package wansbot.tasks;

/**
 * Class which gives WansBot functionality to add tasks with just a name.
 */
public class Todos extends Task {
    /**
     * Assigns name to the Todos.
     */
    public Todos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String myTodo = "[ T ] ";

        if (this.isDone()) {
            myTodo += "[ X ]";
        } else {
            myTodo += "[   ]";
        }
        return myTodo + " " + super.toString();
    }
}
