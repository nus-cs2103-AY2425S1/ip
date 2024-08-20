public class Event extends Deadline{
    private String from;

    public Event(String description, String from, String by){
        super(description,by);
        this.from = from;
    }

    public String getFrom(){
        return this.from;
    }

    public void setForm(String from){
        this.from = from;
    }

    public String type(){
        return "E";
    }

    @Override
    public String toString(){
        return " ["+this.type()+"]["+this.getStatusIcon()+"] "+ this.getDescription()+" (from: "+this.from + " to: " + super.by+ ")";
    }
}
