package echochat;
public class Todo extends Task{

    public Todo(String desc) {
        super('T',desc);
    }


    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Todo.class) {
            return ((Todo) o).getDesc().equals(this.getDesc());
        }
        return false;
    }


}
