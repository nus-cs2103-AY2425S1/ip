public class ToDoTask extends Task{
    
    ToDoTask (String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
