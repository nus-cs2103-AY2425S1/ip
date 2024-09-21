package shnoop.tasks;

/**
 * Encapsulates a task that does not have any time-related details.
 */
public class Todo extends Task {

    /**
     * Creates a Todo Task based on the given description.
     *
     * @param description Details of the Todo Task.
     */
    public Todo(String description) {
        super(description.substring(5, description.length()));
    }

    /**
     * Creates a Todo Task based on the given description, but also mark it as done.
     * Typically used by FileWriter / FileReader when loading from Storage and adding to TaskList.
     *
     * @param description Details of the Todo Task.
     * @param done True if the Todo Task created should be marked as done.
     */
    public Todo(String description, boolean done) {
        super(description);
        if (done) {
            this.markTask();
        }
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    // @@author GeeksForGeeks
    // Reused from https://www.geeksforgeeks.org/overriding-equals-method-in-java/
    // with minor modifications
    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Todo or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Todo)) {
            return false;
        }

        // typecast o to Todo so that we can compare data members
        Todo c = (Todo) o;

        // Compare the data members and return accordingly
        return CharSequence.compare(description, c.description) == 0
                && Boolean.compare(isDone, c.isDone) == 0;
    }
    // @@author GeeksForGeeks

    @Override
    public String toUniqueFileString() {
        String s = super.toUniqueFileString();
        s += "001"; // Unique identifier for Todo Tasktype
        s += super.description;
        return s;
    }
}
