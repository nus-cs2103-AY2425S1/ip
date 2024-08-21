public class ToDo extends Task{
    public ToDo (String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
