package duch.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// dates inputted must be in the format d/MM/yyyy HHmm
public class Date {
    LocalDateTime dateTime;
    static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");

    public Date(String str) {
        dateTime = LocalDateTime.parse(str, inputFormatter);
    }

    /**
     * Outputs the date formatted in MMM-dd-yyyy HHmm.
     * 
     * @return The string.
     */
    public String toString() {
        return dateTime.format(outputFormatter);
    }

    
    /**
     * Outputs the date formatted in d/MM/yyyy HHmm, i.e in the original format the user inputs with
     * for storage.
     * 
     * @return The string.
     */
    public String toStore() {
        return dateTime.format(inputFormatter);
    }

    public static void main(String[] args) {
        String d = "Dec-02-2009 1800";
        try {
            System.out.println(new Date(d).toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
