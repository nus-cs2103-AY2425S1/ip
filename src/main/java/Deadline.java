public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description,TaskType.DEADLINE);
        this.by = by;
    }

    public String getBy(){
        return this.by;
    }

    public void setBy(String by){
        this.by = by;
    }

    @Override
    public TaskType type() {
        return TaskType.DEADLINE;
    }

    @Override
    public String toString(){
        return " ["+this.type()+"]["+this.getStatusIcon()+"] "+ this.getDescription()+" (by: "+this.by +")";
    }


}