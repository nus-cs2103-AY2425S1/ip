package com.nimbus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event is a task with start time and end time
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        this(description, false, from, to);
    }

    /**
     * Create a new event
     * @param description description of the event
     * @param isDone True iff the event has been done
     * @param from Start time of the event
     * @param to End time of the event
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        try {
            LocalDate date = LocalDate.parse(from);
            this.from = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.from = from;
        }

        try {
            LocalDate date = LocalDate.parse(to);
            this.to = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.to = to;
        }
    }

    /**
     * Return from date of an event
     * @return from date in a String
     */
    public String getFromDate() {
        return this.from;
    }

    /**
     * Return to date of an event
     * @return to date in a String
     */
    public String getToDate() {
        return this.to;
    }

    @Override
    public String toFileFormat() {
        String isDoneString = this.isDone ? "1" : "0";
        String commandHeader = getTypeIcon().charAt(1) + "|" + isDoneString;
        String commandDescription = this.getDescription() + "|" + this.getFromDate() + "|" + this.getToDate();
        return commandHeader + "|" + commandDescription;
    }

    @Override
    public String toString() {
        String date = " (from: " + this.from + " to: " + this.to + ")";
        return getTypeIcon() + super.toString() + date;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public boolean contains(String keyword) {
        return getDescription().contains(keyword);
    }
}
