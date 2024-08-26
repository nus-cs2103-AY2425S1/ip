public class ToDos extends Task{
    public ToDos(String content) {

        super(content);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
