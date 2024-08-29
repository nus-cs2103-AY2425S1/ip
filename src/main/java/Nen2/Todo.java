package Nen2;

/**
 * This class represents a Todo Task
 * @author Gan Ren Yick (A0276246X)
 */

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "T/" + this.isDone + "/" + this.description ;
    }
}
