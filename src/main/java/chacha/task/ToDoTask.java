package chacha.task;

/**
 * Represents the Task that is a To Do for the user.
 *
 */
public class ToDoTask extends Task {
    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation to be printed.
     *
     * @return String representation.
     */
    @Override
    public String printTask() {
        String output = "[T]";
        String status = (super.isDone ? "X" : " ");
        return output + "[" + status + "] " + super.description;
    }

    /**
     * Returns a string representation to be written in chacha.txt.
     *
     * @return String representation.
     */
    @Override
    public String writeTask() {
        String output = "T | ";
        String status = (super.isDone ? "1" : "0");
        output += status + " | ";
        return output + description + "\n";
    }
}
