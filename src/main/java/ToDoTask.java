public class ToDoTask extends Task{
    ToDoTask(String desc){
        super('T', desc);
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
