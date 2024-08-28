package bro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses a date and time from a string using the format "yyyy-MM-dd HHmm".
     *
     * @param s The string representation of the date and time.
     * @return A LocalDateTime object representing the parsed date and time, or null if parsing fails.
     */
    public LocalDateTime parseDate(String s) {
        try {
            return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException dte) {
            System.out.println("   Input date and time format at yyyy-mm-dd tttt");
        }
        return null;
    }

    /**
     * Parses a string to create a Deadline task and adds it to the task list. The string is expected to contain
     * the task description and a deadline, separated by " /by ".
     *
     * @param s      The string containing the task description and deadline.
     * @param tasks  The TaskList object to which the new Deadline task will be added.
     */
    public void parseDeadline(String s, TaskList tasks) {
        String[] info = s.split(" /by ", 2);
        LocalDateTime by = this.parseDate(info[1]);
        if (by != null) {
            Task curr = new Deadline(info[0], by);
            tasks.add(curr);
            ui.printStatus(curr, tasks.size());
        }
    }

    /**
     * Parses a string to create an Event task and adds it to the task list. The string is expected to contain
     * the task description, start time, and end time, separated by " /from " and " /to " respectively.
     *
     * @param s      The string containing the task description, start time, and end time.
     * @param tasks  The TaskList object to which the new Event task will be added.
     */
    public void parseEvent(String s, TaskList tasks) {
        String[] info = s.split(" /from | /to ", 3);
        LocalDateTime from = this.parseDate(info[1]);
        if (from != null) {
            LocalDateTime to = this.parseDate(info[2]);
            if (to != null) {
                Task curr = new Event(info[0], from, to);
                tasks.add(curr);
                ui.printStatus(curr, tasks.size());
            }
        }
    }
}
