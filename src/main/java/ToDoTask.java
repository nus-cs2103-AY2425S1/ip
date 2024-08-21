/**
* This class defines and prodides functionality for todo tasks.
*/
public class ToDoTask extends Task{
    
    /**
     * Constructor to create a ToDo task object.
     * @param taskName Description of the task
     */
    ToDoTask (String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
