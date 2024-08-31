public class Todo extends Task{


    public String getTypeLetter() {
        return "T";
    }
    public Todo(String taskDes) {
        super(taskDes);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String getAdd() {
        return "";
    }
}
