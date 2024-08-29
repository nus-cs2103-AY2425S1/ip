import java.time.LocalDateTime;

public class Deadline extends Task{
    protected LocalDateTime endDate;

    public Deadline(String description, LocalDateTime endDate){
        super(description);
        this.endDate = endDate;
    }
    @Override
    public String listedString(){
        return super.listedString() + " [by " + super.localDateTimeToString(this.getEndDate()) + "]";
    }
    public LocalDateTime getEndDate(){ return endDate; }


}