package sinatra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {




    private String dateTimeString;
    private LocalDateTime dateTime;


    public static Deadline newObjectFromData(String data) {
        String[] parts = data.split(",");
        return new Deadline(parts[0], Boolean.parseBoolean(parts[1]), parts[2]);


    }
    public Deadline(String content, Boolean status,String dateTimeString) {
        super(content, status);
this.dateTimeString = dateTimeString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.dateTime = LocalDateTime.parse(dateTimeString, formatter);



    }

    public String getDataForStorage() {
        return "Sinatra.Deadline:" + super.getContent() + "," + super.isMarkedString() + "," + dateTimeString;
    }


    public String getBy() {
        return dateTimeString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeString + ")";
    }


}
