public class ToDos extends Task{
    ToDos(String content) {
        super(content);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
