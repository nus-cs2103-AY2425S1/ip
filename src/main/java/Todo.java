public class Todo extends Task{
    public Todo(String des) {
        super(des);
    }

    public Todo(String des, boolean isMark) {
        super(des);
        this.isMark = isMark;
    }
    @Override
    public String getDes() {
        return "[T]" + super.getDes();
    }

    @Override
    public String toString() {
        return String.format("T %d %s", isMark? 1 : 0, des);
    }
}
