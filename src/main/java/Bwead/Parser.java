package Bwead;

import java.time.LocalTime;
import java.util.Scanner;
import java.time.LocalDate;

public class Parser {

    private static Scanner scanner = new Scanner(System.in);
    private String command;

    public Parser(String command) {
        this.command = command;
    }

    public boolean isOneWord() {
        return command.indexOf(" ") == -1;
    }

    public boolean isInvalidMultiWord() {
        return !command.startsWith("todo") && !command.startsWith("deadline") && !command.startsWith("event")
                && !command.startsWith("delete") && !command.startsWith("mark") && !command.startsWith("unmark");
    }

    public int getTaskToMark() {
        return Integer.valueOf(command.split(" ")[1]);
    }

    public String getTodoName() {
        return command.replace("todo ", "");
    }

    public String getDeadlineName() {
        String input1 = command.replace("deadline ", "");
        int slash = input1.indexOf("/");
        return input1.substring(0, slash - 1);
    }

    public LocalDate getDeadlineDate() {
        String dateString = command.split("/by ")[1];
        dateString = dateString.substring(0, dateString.length() - 5);
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }

    public String getEventName() {
        String input = command.replace("event ", "");
        return input.split("/from")[0];
    }

    public LocalDate getEventStart() {
        String startString = command.split("/from ")[1].split(" /to")[0];
        LocalDate start = LocalDate.parse(startString.substring(0, startString.length() - 5));
        return start;
    }

    public LocalDate getEventEnd() {
        String endString = command.split("/from ")[1].split("/to ")[1];
        LocalDate end = LocalDate.parse(endString.substring(0, endString.length() - 5));
        return end;
    }

    public int getDeleteIndex() {
        return Integer.valueOf(command.split(" ")[1]);
    }

    public LocalTime getDeadlineTime() {
        String dateTimeString = command.split("/by ")[1];
        String timeString = dateTimeString.substring(dateTimeString.length() - 4, dateTimeString.length());
        return LocalTime.parse(timeString.substring(0, 2) + ":" + timeString.substring(2,4));
    }

    public LocalTime getEventStartTime() {
        String startDateTimeString = command.split("from ")[1].split(" /to")[0];
        String startTimeString = startDateTimeString.substring(startDateTimeString.length() - 4,
                startDateTimeString.length());
        return LocalTime.parse(startTimeString.substring(0,2) + ":" + startTimeString.substring(2,4));
    }

    public LocalTime getEventEndTime() {
        String endDateTimeString = command.split("to ")[1];
        String endTimeString = endDateTimeString.substring(endDateTimeString.length() - 4,
                endDateTimeString.length());
        return LocalTime.parse(endTimeString.substring(0,2) + ":" + endTimeString.substring(2,4));
    }

}
