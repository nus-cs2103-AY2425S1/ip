public class ToDo extends Task{


    /**
     * Constructs a new 'Todo' event that can be added to Tayoo's list. A Todo is similar to a task,
     * except the string representation is different.
     *
     * @param title title of the Todo.
     */
    public ToDo (String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
