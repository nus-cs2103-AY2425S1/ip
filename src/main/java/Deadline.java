public class Deadline extends Todo{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy(){
        return this.by;
    }

    public void setBy(String by){
        this.by = by;
    }

    public String type(){
        return "D";
    }

    @Override
    public String toString(){
        return " ["+this.type()+"]["+this.getStatusIcon()+"] "+ this.getDescription()+" (by: "+this.by +")";
    }


}