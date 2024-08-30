package task;

import java.util.ArrayList;

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
