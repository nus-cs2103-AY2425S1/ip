package Stobberi.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    public Todo(String description, String done) {
        super(description);
        if (done.equals("1")) {super.setDone();}
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}