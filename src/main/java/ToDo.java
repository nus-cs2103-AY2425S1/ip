public class ToDo extends Task{


    /**
     * Constructs a new 'Todo' event that can be added to Tayoo's list. A Todo is similar to a task,
     * except the string representation is different.
     *
     * @param title title of the Todo.
     */
    public ToDo (String title, boolean completed) {
        super(title, completed);
    }

    /**
     * Constructs a new 'Todo' event that can be added to Tayoo's list. A Todo is similar to a task,
     * except the string representation is different. Assumes new Todo is not completed.
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

    /**
     * Returns the Todo in a form that can be stored as data in a .txt file
     * Tasks will be stored in the format "Todo" | [true OR false] | [Todo title]. The value true will
     * be stored if the Todo is completed, and false otherwise.
     *
     * @return string representation of Todo in command form
     */
    public String toTxt() {
        if (this.isCompleted()) {
            return "Todo | true | " + this.getTitle();
        } else {
            return "Todo | false | " + this.getTitle();
        }
    }
}
