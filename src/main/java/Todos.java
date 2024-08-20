public class Todos extends Task{
    public Todos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String myTodo = "[ T ] ";

        if (this.isDone()) {
            myTodo += "[ X ]";
        } else {
            myTodo += "[   ]";
        }
        return myTodo + super.toString();
    }
}
