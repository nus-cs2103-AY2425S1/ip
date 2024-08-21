public class Event extends Task{
    private String to;
    private String from;

    public Event(String description, String from, String to){
        super(description,TaskType.EVENT);
        this.to = to;
        this.from = from;
    }

    public String getFrom(){
        return this.from;
    }

    public void setForm(String from){
        this.from = from;
    }

    @Override
    public TaskType type() {
        return TaskType.EVENT;
    }

    @Override
    public String toString(){
        return " ["+this.type()+"]["+this.getStatusIcon()+"] "+ this.getDescription()+" (from: "+this.from + " to: " + this.to+ ")";
    }
}
