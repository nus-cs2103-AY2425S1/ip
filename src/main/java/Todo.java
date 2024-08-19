public class Todo extends Task{
    public Todo(String des) {
        super(des);
    }

    @Override
    public String getDes() {
        return "[T]" + super.getDes();
    }
}
