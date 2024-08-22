public class Todo extends Task{

    public Todo(String str) {
        super(str);
    }

    @Override
    public String toString() {
        String str = this.isDone ? "[T][X] " : "[T][ ] ";
        str += this.description.trim();
        return str;
    }
}
