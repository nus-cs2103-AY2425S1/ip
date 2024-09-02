public class ToDoTask extends Task{
    ToDoTask(String desc) throws EmptyDescException {
        super(desc);
    }

    @Override
    public String getOriginalCommand() {
        return "todo " + super.getOriginalCommand();
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
