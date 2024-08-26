
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{
    private final LocalDate dueDate;
    public Deadlines(String description,LocalDate dueDate){
        super(description);
        this.dueDate = dueDate;
    }
    public Deadlines(String description,boolean status,LocalDate dueDate){
        super(description,status);
        this.dueDate = dueDate;
    }
    private String dateToString(LocalDate date,boolean save){
        if (save){
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    
    @Override
    public String getSaveFormat(){
        return String.format("D | %s | %s", super.getSaveFormat(),dateToString(this.dueDate,true));
    }

    @Override
    public String toString(){
        return String.format("[D]%s (by: %s)",super.toString(),dateToString(this.dueDate,false));
    }
}