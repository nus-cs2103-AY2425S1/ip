public class ToDos extends Task{

    public ToDos(String desc) {
        super(desc);
    }

    @Override
    public String print() {
        return "[T]" + super.print();
    }
}
