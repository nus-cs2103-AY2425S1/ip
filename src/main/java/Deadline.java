import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String by;
    private LocalDateTime byDateTime; 
    
    public Deadline(String taskName, String by) throws Meowception {
        super(taskName);
        //this.by = by;
        try {
            byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (java.time.format.DateTimeParseException e) {
            throw new Meowception("300");
        }
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm MMM dd yyyy");
        String formattedDateTime = byDateTime.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDateTime + ")";
    }

    @Override
    public String getExtra() {
        return "/by " + byDateTime.toString();
    }
    
    @Override
    public String getType() {
        return "deadline";
    }
}
