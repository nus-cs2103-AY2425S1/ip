public class Todo extends Task{
    public Todo(String name) {
        super(name);
    }


    @Override
    protected String getWriteFormat() {
        return "T , " + (isDone ? "1" : "0") + " , " + name;
    }

    @Override
    public String toString() {
        return "[T]"+super.toString();
    }
}
