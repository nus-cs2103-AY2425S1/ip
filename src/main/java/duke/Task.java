package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Task {
    public static int TODO = 0;
    public static int DEADLINE = 1;
    public static int EVENT = 2;
    /**
     * Returns a Task object
     *
     * @param name the name of the task
     */
    Task(String name) {
        this.name = name;
        this.isMarked = false;
    }

    /**
     * Returns a Task object
     *
     * @param name the name of the task
     * @param type the type of the task (todo/deadline/event)
     */
    Task(String name, int type) {
        this.name = name;
        this.isMarked = false;
        this.type = type;
    }

    /**
     * Returns a Task object
     *
     * @param name the name of the task
     * @param type the type of the task (todo/deadline/event)
     * @param deadline the deadline for the task in case the type is deadline
     */
    Task(String name, int type, String deadline) {
        this.name = name;
        this.isMarked = false;
        this.type = type;

        try {
            LocalDate date = LocalDate.parse(deadline, DateTimeFormatter.ISO_DATE);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd yyyy");

            this.deadline = date.format(myFormatObj);
        } catch (Exception e) {
            this.deadline = deadline;
        }
    }

    /**
     * Returns a Task object
     *
     * @param name the name of the task
     * @param type the type of the task (todo/deadline/event)
     * @param eventTimings the start and end timings for the task in
     * case its type is event
     */
    Task(String name, int type, String[] eventTimings) {
        this.name = name;
        this.isMarked = false;
        this.type = type;

        try {
            LocalDate date = LocalDate.parse(eventTimings[0], DateTimeFormatter.ISO_DATE);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd yyyy");

            eventTimings[0] = date.format(myFormatObj);
        } catch (Exception e) {
            // do nothing
            System.out.println(e);
        }

        try {
            LocalDate date = LocalDate.parse(eventTimings[1], DateTimeFormatter.ISO_DATE);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd yyyy");

            eventTimings[1] = date.format(myFormatObj);
        } catch (Exception e) {
            // do nothing
        }

        this.eventTimings = eventTimings;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    public String print() {
        String message = "";

        if(this.type == TODO) {
            message += "[T]";
        } else if(this.type == DEADLINE) {
            message += "[D]";
        } else if (this.type == EVENT) {
            message += "[E]";
        } else {
            message += "[ ]";
        }

        if(this.isMarked) {
            message += "[X] ";
        } else {
            message += "[ ] ";
        }

        message += name;

        if(this.type == DEADLINE) {
            message += " (by: " + this.deadline + ")";
        } else if (this.type == EVENT) {
            message += " (from: " + this.eventTimings[0] + " to: " + this.eventTimings[1] + ")";
        }

        System.out.println(message);
        return message;
    }

    public String print(int rank) {
        String message = rank + ".";

        if(this.type == TODO) {
            message += "[T]";
        } else if(this.type == DEADLINE) {
            message += "[D]";
        } else if (this.type == EVENT) {
            message += "[E]";
        } else {
            message += "[ ]";
        }

        if(this.isMarked) {
            message += "[X] ";
        } else {
            message += "[ ] ";
        }

        message += name;

        if(this.type == DEADLINE) {
            message += " (by: " + deadline + ")";
        } else if (this.type == EVENT) {
            message += " (from: " + this.eventTimings[0] + " to: " + this.eventTimings[1] + ")";
        }

        System.out.println(message);
        return message;
    }
    public String name;
    public boolean isMarked;
    public int type;

    public String deadline;

    public String[] eventTimings = new String[2];
}
