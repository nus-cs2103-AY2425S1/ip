package rainy.tasks;
import rainy.database.*;
import rainy.rainyexceptions.*;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] " + super.getName();
    }
}