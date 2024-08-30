
package jeriel.task;
import jeriel.command.*;
import jeriel.util.*;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this task in the save format.
     *
     * @return a string in the format "T | 0/1 | description"
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of this task.
     *
     * @return a string in the format "[T][ ] description" if the task is not done, or "[T][X] description" if the task is done
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
}
