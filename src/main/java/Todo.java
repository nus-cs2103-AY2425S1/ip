public class Todo extends Task{
    public Todo(String name, boolean isDone) {
        super(name,isDone);
        super.type = "T";
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.check() + "] " + this.name;
    }
}

