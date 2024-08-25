public class Todo extends Task{
    public Todo(String task){
        super(task);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }
}
