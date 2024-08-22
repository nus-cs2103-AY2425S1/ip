public class ToDo extends Task{

    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        String str = this.completed ? "[T][X] " : "[T][ ] ";
        str += this.description;
        return str;
    }
}
