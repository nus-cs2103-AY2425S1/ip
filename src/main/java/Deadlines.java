public class Deadlines extends Task{
    private final String dueDate;
    public Deadlines(String description,String dueDate){
        super(description);
        this.dueDate = dueDate;
    }
    public Deadlines(String description,boolean status,String dueDate){
        super(description,status);
        this.dueDate = dueDate;
    }
    @Override
    public String getSaveFormat(){
        return String.format("D | %s | %s", super.getSaveFormat(),this.dueDate);
    }

    @Override
    public String toString(){
        return String.format("[D]%s (by: %s)",super.toString(),dueDate);
    }
}