public class ToDo extends Task {

    ToDo() {
        super();
    }

    ToDo(String todoDesc) {
        super(todoDesc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
