package chatbaby;

public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toFileText() {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

