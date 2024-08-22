public class ToDoTask extends Task{
    ToDoTask(String desc) throws EmptyDescException {
        super(desc);
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
