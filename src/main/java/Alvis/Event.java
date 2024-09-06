package Alvis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Event(String desc, String startTime, String endTime) {
        super(desc);
        this.startTime = LocalDateTime.parse(startTime, formatter);
        this.endTime = LocalDateTime.parse(endTime, formatter);
    }

    @Override
    public String toString() {
        return String.format("[E]%s(from: %s to: %s)", super.toString()
                , startTime.format(formatter), endTime.format(formatter));
                
    }

    public String toSaveFormat() {
        return String.format("E_%s_%s_%s_%s", isDone ? "1" : "0", getDesc()
                , startTime.format(formatter), endTime.format(formatter));
    }
}
