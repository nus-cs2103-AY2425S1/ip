package com.meow.com.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.meow.Meowception;

public class Deadline extends Task {
    private LocalDateTime byDateTime; 
    
    public Deadline(String taskName, String by) throws Meowception {
        super(taskName);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "/by " + byDateTime.format(formatter);
    }
    
    @Override
    public String getType() {
        return "deadline";
    }

    public void setNewTime(String newTime) throws Meowception {
        try {
            byDateTime = LocalDateTime.parse(newTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (java.time.format.DateTimeParseException e) {
            throw new Meowception("300");
        }
    }
}
