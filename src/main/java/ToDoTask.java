public class ToDoTask extends Task {
    public ToDoTask(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        String s = "";
        s += "[T]";
        s += super.toString();
        s += super.taskDesc;
        return s;
    }
}
