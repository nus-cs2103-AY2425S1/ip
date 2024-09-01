package Papagu.Ui;

/**
 * The ToDos class represents a task with just a description
 */
public class ToDos extends Task {

    /**
     * Constuctor for a new todo object
     * @param description
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns the string representation of task for saving to tasks.txt
     * @return
     */
    @Override
    public String toFile() {
        return "T | " + (super.isDone() ? "1" : "0")
                + " | " + super.getDescription();
    }

    /**
     * Returns the string representation of the task for printing in Ui
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
}
