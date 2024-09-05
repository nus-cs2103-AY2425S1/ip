package Diomon;

public class Todo extends Task{
    public static final String TYPEICON = "T";
    public Todo(String task){
        super(task);
    }
    public Todo(boolean complete, String description) {
        super(complete, description);

    }

    @Override
    public String getTypeIcon() {
        return TYPEICON;
    }
    @Override
    public String toStorageString(){
        return String.format("%s|%s|%s", TYPEICON, getStatusIcon(),this.description);
    }
}
