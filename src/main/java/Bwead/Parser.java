package Bwead;

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
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }

    public String getEventName() {
        String input = command.replace("event ", "");
        return input.split("/from")[0];
    }

    public LocalDate getEventStart() {
        String startString = command.split("/from ")[1].split(" /to")[0];
        LocalDate start = LocalDate.parse(startString);
        return start;
    }

    public LocalDate getEventEnd() {
        String endString = command.split("/from ")[1].split("/to ")[1];
        LocalDate end = LocalDate.parse(endString);
        return end;
    }

    public int getDeleteIndex() {
        return Integer.valueOf(command.split(" ")[1]);
    }

}
