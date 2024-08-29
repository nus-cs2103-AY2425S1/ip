package makima.io;

import makima.command.Makima;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {

    public static String getInput() {
        String userInput = Makima.SC.nextLine();
        System.out.println(Makima.LINE_SEPERATOR);
        while (userInput.isEmpty()) {
            System.out.println("Input cannot be empty!");
            System.out.println(Makima.LINE_SEPERATOR);
            userInput = Makima.SC.nextLine();
        }
        return userInput;
    }

    public static LocalDateTime getDate() {
        while (true) {
            try {
                return LocalDateTime.parse(getInput());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please input the date as follows: YYYY-MM-DD HH:MM," +
                        "replacing the space with a T");
            }
        }
    }

    public static LocalDateTime getDateAfter(LocalDateTime date) {
        while (true) {
            try {
                LocalDateTime dateAfter = LocalDateTime.parse(getInput());
                if (dateAfter.isAfter(date)) {
                    return dateAfter;
                } else {
                    System.out.println("The specified date must be after the beginning of the event!");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please input the date as follows: YYYY-MM-DD HH:MM," +
                        "replacing the space with a T");
            }
        }
    }
}
