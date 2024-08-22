// class for Tasks which are ToDos
public class ToDos extends Task{
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
