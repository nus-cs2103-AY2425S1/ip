public class Todo extends Task{


    public Todo(String taskDes) {
        super(taskDes);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
