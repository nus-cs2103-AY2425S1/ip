public class Todo extends Task{
    public Todo(String desc, Boolean mark) {
        super(desc, mark);
    }

    @Override
    public String printTask() {
        return String.format("[T]%s", super.printTask());
    }
}
