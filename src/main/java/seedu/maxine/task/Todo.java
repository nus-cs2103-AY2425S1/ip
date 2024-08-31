package seedu.maxine.task;

public class Todo extends Task {

    public Todo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + description;
    }
    
    @Override
    public String writeToFile() {
        return "T" + super.writeToFile();
    }

}
