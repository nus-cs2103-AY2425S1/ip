public class ToDo extends Task {

    public ToDo(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "[T]" + "["+ getStatus() + "]" + " " + getInfo();
    }
}
