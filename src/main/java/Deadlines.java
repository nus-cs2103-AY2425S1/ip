public class Deadlines extends Task{
    private final String dueDate;
    public Deadlines(String description,String dueDate){
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString(){
        return String.format("[D]%s (by: %s)",super.toString(),dueDate);
    }
}