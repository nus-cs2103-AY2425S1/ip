package tasktypes;

public class ToDo extends Task{
    
    public ToDo(String description) {
        super(description);
    }
    
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    
    public String storageFormat () {
        return "T " + super.storageFormat() + "\n";
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
    
    public String getClassName() {
        return TaskName.TODO.getName();
    }
}
