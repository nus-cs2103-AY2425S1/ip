public class Events extends Task{
    private final String fromDate;
    private final String toDate;
    public Events(String description,String fromDate, String toDate){
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString(){
        return String.format("[E]%s (from: %s to: %s)",super.toString(),fromDate,toDate);
    }
}