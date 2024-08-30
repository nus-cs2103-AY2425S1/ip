package kitty;

import kitty.command.*;
import kitty.kittyexceptions.DeadlineException;
import kitty.kittyexceptions.EventException;
import kitty.kittyexceptions.FindException;
import kitty.kittyexceptions.MarksException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public static boolean parseFirstWord(String str, Ui ui, TaskList tasks, Storage storage) throws FindException, MarksException, NumberFormatException, IndexOutOfBoundsException{
        String[] aux = str.split(" ", 2);
        switch (aux[0].trim()) {
            case "find" -> parseFind(str, ui, tasks);
            case "list" -> new ListCommand(ui, tasks).run();
            case "mark", "delete", "unmark" -> parseMarks(str, ui, storage, tasks);
            case "todo", "deadline", "event" -> new AddCommand(ui, tasks, str, storage).run();
            default -> ui.showErrorMessage("Burrrrr~ What is this??? I have no idea about it...");

        }
        return false;
    }

    public static boolean checkDeadline(String str, Ui ui) {
        try {
            String[] parts = str.split("/by");
            if (parts.length != 2 || parts[0].equals(""))
                throw new DeadlineException();
            return checkDateTime(parts[1].trim());
        } catch (DeadlineException e) {
            ui.showErrorMessage(e.toString());
            return false;
        }
    }

    public static String[] parseDeadline(String str) {
        return str.split("/by");
    }

    public static String[] parseEvent(String str) {
        return str.split("/from|/to");
    }

    public static boolean checkEvent(String str, Ui ui) {
        try {
            String[] parts = str.split("/from|/to");
            if (parts.length != 3)
                throw new EventException();
            return checkDateTime(parts[1].trim()) && checkDateTime(parts[2].trim());
        } catch (EventException e) {
            ui.showErrorMessage(e.toString());
            return false;
        }
    }

    private static void parseFind(String str, Ui ui, TaskList tasks) throws FindException{
        String[] aux = str.split(" ");
        if (aux.length != 2) {
            throw new FindException();
        }
        new FindCommand(ui, tasks, aux[1]).run();
    }

    private static boolean checkDateTime(String dateTime) {
        try {
            LocalDateTime.parse(dateTime, DATE_TIME_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDateTime parseDateTime(String str) {
        return LocalDateTime.now();
    }

    private static void parseMarks(String str, Ui ui, Storage storage, TaskList tasks) throws MarksException, NumberFormatException, IndexOutOfBoundsException{
        String[] aux = str.split(" ");
        if (aux.length != 2) {
            throw new MarksException();
        }

        // Replace all non-digit characters with spaces
        String cleanedInput = aux[1].replaceAll("\\D+", " ");
        // Split the cleaned string by spaces
        String[] parts = cleanedInput.trim().split("\\s+");

        int index;
        // Check if there are any parts and parse the first one
        if (parts.length > 0) {
            index = Integer.parseInt(parts[0]);
            switch (aux[0].trim()) {
                case "mark" -> new MarkCommand(ui, tasks, index, storage).run();
                case "unmark" -> new UnmarkCommand(ui, tasks, index, storage).run();
                case "delete" -> new DeleteCommand(ui, tasks, index, storage).run();
            }
        }

    }

}
